package com.example.demo.controller;

//数据交互的接口
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//返回json
@RestController
@RequestMapping("/product")//配置路由
@CrossOrigin
public class ProductController {
    @Resource
    ProductMapper ProductMapper;//实现一个实体先

    @PostMapping//post接口//插入
    public Result<?> save(@RequestBody Product Product){//传来的json对象转换为json实体
        ProductMapper.insert(Product);//能够获取，但是无法加入到数据库中？

    return Result.success();
    }
    @PutMapping//更新
    public Result<?> update(@RequestBody Product Product){//传来的json对象转换为json实体
        ProductMapper.updateById(Product);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }

    @DeleteMapping("/{id}")//删除//"{"是占位符
    public Result<?> update(@PathVariable Long id){//传来的json对象转换为json实体
        ProductMapper.deleteById(id);//能够获取，但是无法加入到数据库中？
        return Result.success();
    }


    @GetMapping//post接口//查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
//        Page<Product> ProductPage = ProductMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Product>lambdaQuery().like(Product::getLogin_name, search));
        LambdaQueryWrapper<Product> wrapper = Wrappers.<Product>lambdaQuery().like(Product::getProductname, search);
//        if (StrUtil.isNotBlank(search)) {//判断search不是空
//            wrapper.like(Product::getProductname, search);
//        }
        Page<Product> ProductPage = ProductMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        Page<Product> productPage =null;
        return Result.success(ProductPage);
    }
}
