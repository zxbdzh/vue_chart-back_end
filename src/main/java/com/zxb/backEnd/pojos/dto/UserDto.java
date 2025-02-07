package com.zxb.backEnd.pojos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(description = "用户信息传输层")
public class UserDto {
    // 用户姓名
    @Schema(description = "用户姓名")
    @Size(max = 8, message = "密码最高8位")
    private String username;
    // 用户密码
    @Schema(description = "用户密码")
    @Size(max = 15, message = "密码最高15位")
    private String password;
}
