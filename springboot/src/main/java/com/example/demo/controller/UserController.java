package com.example.demo.controller;

//数据交互的接口
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//返回json
@RestController
@RequestMapping("/user")//配置路由
@CrossOrigin
public class UserController {
    @Resource
    UserMapper userMapper;//实现一个实体先


    @PostMapping("/login")//post接口//登录
    public Result<?> login(@RequestBody User user){//传来的json对象转换为json实体
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getLoginname,user.getLoginname()).eq(User::getPassword,user.getPassword()));

        //只能有一个账户
        if (res==null){
           return Result.error("-1","用户名或密码有误");
       }
        return Result.success(res);
    }


    @PostMapping("/register")//post接口//注册
    public Result<?> register(@RequestBody User user){//传来的json对象转换为json实体
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getLoginname,user.getLoginname()));
        if (res!=null){
            return Result.error("-1","用户名重复了！");
        }
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody User user){//传来的json对象转换为json实体
    if (user.getPassword()==null){
        user.setPassword("12345678");
    }
        userMapper.insert(user);//能够获取，但是无法加入到数据库中？

    return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody User user){//传来的json对象转换为json实体
        userMapper.updateById(user);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        userMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().like(User::getLoginname, search);
        if (StrUtil.isNotBlank(search)) {//判断search不是空
            wrapper.like(User::getLoginname, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
