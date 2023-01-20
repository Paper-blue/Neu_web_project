package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;

import javax.annotation.Resource;

//通过mapper插入数据库
public interface UserMapper extends BaseMapper<User> {//接口范性型为user

}
