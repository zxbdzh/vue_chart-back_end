package com.zxb.backEnd.controller;

import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.dto.UserDto;
import com.zxb.backEnd.service.UserService;
import com.zxb.backEnd.utils.Result;
import com.zxb.backEnd.utils.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "查询用户信息")
    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        Integer userId = UserContext.getUser().getId();
        if (userId == null) return Result.error("用户未登录");
        return userService.getById(userId);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

}
