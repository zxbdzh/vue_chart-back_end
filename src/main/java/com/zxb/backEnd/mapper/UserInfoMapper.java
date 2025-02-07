package com.zxb.backEnd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxb.backEnd.pojos.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<User> {
}
