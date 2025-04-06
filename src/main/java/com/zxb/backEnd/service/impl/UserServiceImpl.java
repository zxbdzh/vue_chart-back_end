package com.zxb.backEnd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxb.backEnd.mapper.UserMapper;
import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.dto.UserDto;
import com.zxb.backEnd.service.UserService;
import com.zxb.backEnd.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userInfoMapper;

    /**
     * 根据id获取用户信息
     */
    @Override
    public User getById(Integer id) {
        User userInfo = userInfoMapper.selectById(id);
        if (userInfo == null) return null;
        userInfo.setPassword("********");
        return userInfo;
    }

    @Override
    public boolean register(UserDto userDto) {
        String username = userDto.getUsername();

        // 查询数据库是否存在用户
        User user = query().eq("username", username).one();
        if (user != null) return false;

        // 注册用户
        user = new User();
        BeanUtils.copyProperties(userDto, user);
        // password加密
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);

        // 创建和修改时间
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 存入数据库
        save(user);
        return true;

    }

    /**
     * 用户登录
     *
     */
    @Override
    public String login(UserDto userDto) {
        // 查询数据库, 判断用户是否存在
        User user = query().eq("username", userDto.getUsername()).one();
        if (user == null) return null;

        // 判断密码是否一致
        String password = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        if (!password.equals(user.getPassword())) return "password error";

        // 登录成功,生成token
        return JwtUtils.genAccessToken(user);
    }
}
