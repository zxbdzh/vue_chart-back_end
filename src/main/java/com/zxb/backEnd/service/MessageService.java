package com.zxb.backEnd.service;

import com.zxb.backEnd.pojos.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxb.backEnd.pojos.dto.MessageDTO;
import com.zxb.backEnd.pojos.vo.MessageVO;

import java.util.List;

/**
* @author zxb
* @description 针对表【message(消息表)】的数据库操作Service
* @createDate 2025-04-01 21:13:16
*/
public interface MessageService extends IService<Message> {

    /**
     * 根据发送和接收端返回message
     */
    List<MessageVO> findByReceiverAndSender(Integer senderId ,Integer receiverId);

    /**
     * 发送消息
     * @param dto
     * @return
     */
    boolean sendMessage(MessageDTO dto);
}
