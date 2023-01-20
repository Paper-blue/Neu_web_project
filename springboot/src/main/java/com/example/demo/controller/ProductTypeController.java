package com.example.demo.controller;

//数据交互的接口
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ProductType;
import com.example.demo.mapper.ProductTypeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//返回json
@RestController
@RequestMapping("/producttype")//配置路由
@CrossOrigin
public class ProductTypeController {
    @Resource
    ProductTypeMapper ProductTypeMapper;//实现一个实体先

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody ProductType ProductType){//传来的json对象转换为json实体
        ProductTypeMapper.insert(ProductType);//能够获取，但是无法加入到数据库中？

    return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody ProductType ProductType){//传来的json对象转换为json实体
        ProductTypeMapper.updateById(ProductType);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        ProductTypeMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
//        Page<ProductType> ProductTypePage = ProductTypeMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<ProductType>lambdaQuery().like(ProductType::getLogin_name, search));
        LambdaQueryWrapper<ProductType> wrapper = Wrappers.<ProductType>lambdaQuery().like(ProductType::getTypename, search);
//        if (StrUtil.isNotBlank(search)) {//判断search不是空
//            wrapper.like(ProductType::getProductTypename, search);
//        }
        Page<ProductType> ProductTypePage = ProductTypeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        Page<ProductType> ProductTypePage =null;
        return Result.success(ProductTypePage);
    }
}
