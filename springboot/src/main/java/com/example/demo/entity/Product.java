package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("product")
@Data

public class Product {
    @TableId(value ="id" , type = IdType.AUTO)
    private Integer id;
    private String typeid;
    private String productname;
    private String norms;
    private String describing;
    private String productno;

}
