package com.zxb.backEnd.pojos.dto;

import lombok.Data;

/**
 * Author: guo'cha
 * CreateTime: 2025/4/2
 * Project: back_end
 */
@Data
public class MessageDTO {

    /**
     * 文章内容
     */
    private String content;

    /**
     * 接受者id
     */
    private Integer id;
}