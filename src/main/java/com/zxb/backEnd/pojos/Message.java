package com.zxb.backEnd.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "聊天信息")
public class Message {
    // id
    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 聊天内容
    @Schema(description = "聊天内容")
    @Size(max = 50, message = "聊天内容不能超过50字！")
    private String content;
    // 更新时间
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    // 创建时间
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
