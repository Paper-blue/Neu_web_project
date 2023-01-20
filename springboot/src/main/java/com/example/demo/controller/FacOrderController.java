package com.example.demo.controller;

//数据交互的接口
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.FacOrder;
import com.example.demo.mapper.FacOrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//返回json
@RestController
@RequestMapping("/facorder")//配置路由
@CrossOrigin
public class FacOrderController {
    @Resource
    FacOrderMapper FacOrderMapper;//实现一个实体先

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody FacOrder FacOrder){//传来的json对象转换为json实体
        FacOrderMapper.insert(FacOrder);//能够获取，但是无法加入到数据库中？

    return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody FacOrder FacOrder){//传来的json对象转换为json实体
        FacOrderMapper.updateById(FacOrder);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        FacOrderMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
//        Page<FacOrder> FacOrderPage = FacOrderMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<FacOrder>lambdaQuery().like(FacOrder::getLogin_name, search));
        LambdaQueryWrapper<FacOrder> wrapper = Wrappers.<FacOrder>lambdaQuery().like(FacOrder::getOrderstatus, search);
//        if (StrUtil.isNotBlank(search)) {//判断search不是空
//            wrapper.like(FacOrder::getFacOrdername, search);
//        }
        Page<FacOrder> FacOrderPage = FacOrderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        Page<FacOrder> FacOrderPage =null;
        return Result.success(FacOrderPage);
    }
}
