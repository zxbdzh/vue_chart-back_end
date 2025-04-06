package com.zxb.backEnd.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 消息表
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 聊天时间
     */
    private LocalDateTime date;

    /**
     * 发送者id
     */
    private Integer senderId;

    /**
     * 接受者id
     */
    private Integer receiverId;
}