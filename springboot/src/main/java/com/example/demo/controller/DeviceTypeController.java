package com.example.demo.controller;

//数据交互的接口
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.DeviceType;
import com.example.demo.mapper.DeviceTypeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//返回json
@RestController
@RequestMapping("/devicetype")//配置路由
@CrossOrigin
public class DeviceTypeController {
    @Resource
    DeviceTypeMapper DeviceTypeMapper;//实现一个实体先

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody DeviceType DeviceType){//传来的json对象转换为json实体
        DeviceTypeMapper.insert(DeviceType);//能够获取，但是无法加入到数据库中？

    return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody DeviceType DeviceType){//传来的json对象转换为json实体
        DeviceTypeMapper.updateById(DeviceType);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        DeviceTypeMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
//        Page<DeviceType> DeviceTypePage = DeviceTypeMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<DeviceType>lambdaQuery().like(DeviceType::getLogin_name, search));
        LambdaQueryWrapper<DeviceType> wrapper = Wrappers.<DeviceType>lambdaQuery().like(DeviceType::getTypename, search);
//        if (StrUtil.isNotBlank(search)) {//判断search不是空
//            wrapper.like(DeviceType::getDeviceTypename, search);
//        }
        Page<DeviceType> DeviceTypePage = DeviceTypeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        Page<DeviceType> DeviceTypePage =null;
        return Result.success(DeviceTypePage);
    }
}
