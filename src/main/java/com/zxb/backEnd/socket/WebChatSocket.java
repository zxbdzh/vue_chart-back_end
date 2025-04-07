package com.zxb.backEnd.socket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/api/socket/chat/{roomId}")
public class WebChatSocket {

    // 房间会话映射: roomId -> (sessionId -> Session)
    private static final ConcurrentHashMap<Integer, ConcurrentHashMap<String, Session>> roomSessions = new ConcurrentHashMap<>();

    // 当前会话的 roomId
    private Integer currentRoomId;

    // 房间广播方法
    public static void broadcastToRoom(Integer roomId, String message) {
        ConcurrentHashMap<String, Session> sessions = roomSessions.get(roomId);
        if (sessions == null) return;

        sessions.forEach((id, session) -> {
            if (session.isOpen()) session.getAsyncRemote().sendText(message);
        });
    }

    // 获取房间大小
    private static int getRoomSize(Integer roomId) {
        return roomSessions.getOrDefault(roomId, new ConcurrentHashMap<>()).size();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") Integer roomId) {
        this.currentRoomId = roomId;

        roomSessions.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>()).put(session.getId(), session);

        log.info("roomId: {} 新连接, 房间连接数: {}", roomId, getRoomSize(roomId));
    }

    @OnClose
    public void onClose(Session session) {
        roomSessions.get(currentRoomId).remove(session.getId());

        // 清理空房间
        if (getRoomSize(currentRoomId) == 0) roomSessions.remove(currentRoomId);

        log.info("roomId: {} 连接关闭, 剩余连接: {}", currentRoomId, getRoomSize(currentRoomId));
    }

    @OnMessage
    public void onMessage(String message) {
        // 向当前房间广播
        broadcastToRoom(currentRoomId, message);
    }

}

