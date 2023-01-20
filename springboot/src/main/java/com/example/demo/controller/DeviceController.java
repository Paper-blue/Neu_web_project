package com.example.demo.controller;

//数据交互的接口
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Device;
import com.example.demo.mapper.DeviceMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//返回json
@RestController
@RequestMapping("/device")//配置路由
@CrossOrigin
public class DeviceController {
    @Resource
    DeviceMapper DeviceMapper;//实现一个实体先

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody Device Device){//传来的json对象转换为json实体
        DeviceMapper.insert(Device);//能够获取，但是无法加入到数据库中？

    return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody Device Device){//传来的json对象转换为json实体
        DeviceMapper.updateById(Device);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        DeviceMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
//        Page<Device> DevicePage = DeviceMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Device>lambdaQuery().like(Device::getLogin_name, search));
        LambdaQueryWrapper<Device> wrapper = Wrappers.<Device>lambdaQuery().like(Device::getDevicename, search);
//        if (StrUtil.isNotBlank(search)) {//判断search不是空
//            wrapper.like(Device::getDevicename, search);
//        }
        Page<Device> DevicePage = DeviceMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        Page<Device> DevicePage =null;
        return Result.success(DevicePage);
    }
}
