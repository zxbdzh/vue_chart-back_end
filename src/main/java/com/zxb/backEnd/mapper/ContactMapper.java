package com.zxb.backEnd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxb.backEnd.pojos.Contact;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper extends BaseMapper<Contact> {
}
