package com.zxb.backEnd.service;

import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.dto.UserDto;
import com.zxb.backEnd.utils.Result;

public interface UserService {
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    Result<User> getById(Integer id);

    /**
     * 注册
     * @param userDto
     * @return
     */
    Result<String> register(UserDto userDto);

    /**
     * 用户登录
     * @param userDto
     * @return
     */
    Result<String> login(UserDto userDto);
}
