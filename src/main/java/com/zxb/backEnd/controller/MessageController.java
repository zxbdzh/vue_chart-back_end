package com.zxb.backEnd.controller;

import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.dto.MessageDTO;
import com.zxb.backEnd.pojos.vo.MessageVO;
import com.zxb.backEnd.service.ContactService;
import com.zxb.backEnd.service.MessageService;
import com.zxb.backEnd.utils.Result;
import com.zxb.backEnd.utils.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Tag(name = "消息相关")
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final ContactService contactService;

    @GetMapping("/{id}")
    @Operation(summary = "获取当前用户的和当前联系人的聊天内容")
    public Result<List<MessageVO>> getMessages(@PathVariable Integer id) {
        User user = UserContext.getUser();
        List<MessageVO> messageVOList = messageService.findByReceiverAndSender(user.getId(), id);
        if (messageVOList == null) return Result.error("查询失败");
        return Result.success(messageVOList);
    }

    @PostMapping
    @Operation(summary = "当前用户发送消息")
    public Result<String> sendMessage(@RequestBody MessageDTO dto) {
        if (dto == null) return Result.error("发送失败");
        if (Objects.equals(dto.getId(), UserContext.getUser().getId())) return Result.error("不能给自己发消息!");
        User user = UserContext.getUser();
        // 1. 判断是否有对应联系人
        if (!contactService.findContact(user.getId(), dto.getId())) return Result.error("该联系人不是你的好友!");
        if (!contactService.findContact(dto.getId(), user.getId())) return Result.error("对方已不是你的好友!");
        // 2. 向对应联系人发送消息
        if (!messageService.sendMessage(dto)) return Result.error("发送失败");
        return Result.success("发送成功");
    }
}
