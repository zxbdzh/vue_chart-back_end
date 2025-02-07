package com.zxb.backEnd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxb.backEnd.mapper.UserInfoMapper;
import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.dto.UserDto;
import com.zxb.backEnd.utils.JwtUtils;
import com.zxb.backEnd.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, User> implements com.zxb.backEnd.service.UserService {

    @Value("${user.avatar}")
    String defaultAvatar;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Result<User> getById(Integer id) {
        User userInfo = userInfoMapper.selectById(id);
        if (userInfo == null) return Result.error(401, "没有改用户！");
        userInfo.setId(null);
        userInfo.setPassword("********");
        return Result.success(userInfo);
    }

    @Override
    public Result<String> register(UserDto userDto) {
        String username = userDto.getUsername();

        // 查询数据库是否存在用户
        User user = query().eq("username", username).one();
        if (user != null) return Result.error("该用户已被注册!");

        // 注册用户
        user = new User();
        BeanUtils.copyProperties(userDto, user);
        // password加密
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);

        // 默认头像
        user.setAvatar(defaultAvatar);

        // 创建和修改时间
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 存入数据库
        save(user);
        return Result.success("注册成功");

    }

    /**
     * 用户登录
     * @param userDto
     * @return
     */
    @Override
    public Result<String> login(UserDto userDto) {
        // 查询数据库, 判断用户是否存在
        User user = query().eq("username", userDto.getUsername()).one();
        if (user == null) return Result.error("该用户不存在!");

        // 判断密码是否一致
        String password = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        if (!password.equals(user.getPassword())) return Result.error("密码错误!");

        // 登录成功,生成token
        String token = JwtUtils.genAccessToken(user);
        return Result.success(token);
    }
}
