package com.zxb.backEnd.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@Schema(description = "用户信息")
//@ToString
public class User {
    // 用户id
    @Schema(description = "用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户姓名
    @Schema(description = "用户姓名")
    @Size(max = 8, message = "密码最高8位")
    private String username;
    // 用户密码
    @Schema(description = "用户密码")
    @Size(max = 15, message = "密码最高15位")
    private String password;
    // 头像
    @Schema(description = "头像")
    @URL(message = "请输入正确url！")
    private String avatar;
    // 更新时间
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    // 创建时间
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
