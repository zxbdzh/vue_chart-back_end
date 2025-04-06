package com.zxb.backEnd.service;

import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.dto.UserDto;

public interface UserService {
    /**
     * 根据id查询用户信息
     *
     */
    User getById(Integer id);

    /**
     * 注册
     *
     */
    boolean register(UserDto userDto);

    /**
     * 用户登录
     *
     */
    String login(UserDto userDto);
}
