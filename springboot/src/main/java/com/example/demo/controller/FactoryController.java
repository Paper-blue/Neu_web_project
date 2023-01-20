package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Factory;
import com.example.demo.mapper.FactoryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RestController
@RequestMapping("/factory")//配置路由
@CrossOrigin
public class FactoryController {
    @Resource
    FactoryMapper factoryMapper;//实现一个实体先

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody Factory factory){//传来的json对象转换为json实体
        factoryMapper.insert(factory);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody Factory factory){//传来的json对象转换为json实体
        factoryMapper.updateById(factory);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        factoryMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
//        Page<factory> factoryPage = factoryMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<factory>lambdaQuery().like(factory::getLogin_name, search));
        LambdaQueryWrapper<Factory> wrapper = Wrappers.<Factory>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {//判断search不是空
            wrapper.like(Factory::getFactoryname, search);
        }
        Page<Factory> factoryPage = factoryMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(factoryPage);
    }
}
