package com.zxb.backEnd.pojos.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageVO {
    /**
     * id
     */
    private Integer id;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 聊天时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /**
     * 类型（回复|发送）
     */
    private String type;
}
