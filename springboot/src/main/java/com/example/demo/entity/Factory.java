package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("factory")
@Data
public class Factory {
    @TableId(value ="id" , type = IdType.AUTO)
    private Integer id;
    private String factoryname;
    private String introduction;
    private String userid;
    private String status;
}
