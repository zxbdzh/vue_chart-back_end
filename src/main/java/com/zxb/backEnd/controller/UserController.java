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
    @GetMapping("/info")
    public Result<User> getUserInfo() {
        Integer userId = UserContext.getUser().getId();
        if (userId == null) return Result.error("用户未登录");
        User user = userService.getById(userId);
        if (user == null) return Result.error(401, "没有该用户！");
        return Result.success(user);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDto userDto) {
        if (!userService.register(userDto)) return Result.error("注册失败");
        return Result.success("注册成功");
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDto userDto) {
        String token = userService.login(userDto);
        if (token == null || token.isEmpty()) return Result.error("登录失败!");
        if (token.equals("password error")) return Result.error("密码错误!");
        return Result.success(token);
    }

}
