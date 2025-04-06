package com.zxb.backEnd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxb.backEnd.mapper.MessageMapper;
import com.zxb.backEnd.pojos.Message;
import com.zxb.backEnd.pojos.dto.MessageDTO;
import com.zxb.backEnd.pojos.vo.MessageVO;
import com.zxb.backEnd.service.MessageService;
import com.zxb.backEnd.utils.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zxb
 * @description 针对表【message(消息表)】的数据库操作Service实现
 * @createDate 2025-04-01 21:13:16
 */
@Service
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    /**
     * 根据发送和接收端返回message
     *
     * @param senderId
     * @param receiverId
     * @return
     */
    public List<MessageVO> findByReceiverAndSender(Integer senderId, Integer receiverId) {
        List<Message> list = query().eq("sender_id", senderId).eq("receiver_id", receiverId).or().eq("sender_id", receiverId).eq("receiver_id", senderId).orderByAsc("date").list();
        List<MessageVO> voList = new ArrayList<>();
        for (Message message : list) {
            MessageVO messageVO = new MessageVO(message.getId(), message.getContent(), message.getDate(), Objects.equals(message.getSenderId(), senderId) ? "sent" : "received");
            voList.add(messageVO);
        }
        return voList;
    }

    /**
     * 发送消息
     * @param dto
     * @return
     */
    public boolean sendMessage(MessageDTO dto) {
        Message message = new Message();
        message.setContent(dto.getContent());
        message.setReceiverId(dto.getId());
        message.setDate(LocalDateTime.now());
        message.setSenderId(UserContext.getUser().getId());
        return save(message);
    }
}




