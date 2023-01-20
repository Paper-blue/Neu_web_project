package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("facorder")
@Data

public class FacOrder {
    @TableId(value ="id" , type = IdType.AUTO)
    private Integer id;
    private String orderno;
    private String productid;
    private String ordernum;
    private String deaddate;
    private String deliverdate;
    private String orderstatus;
    private String userid;
    private String factoryid;
    private String receipt;
    private String contact;
    private String recname;
    private String contid;



}
