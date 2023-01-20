package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("device")
@Data
public class Device {
    @TableId(value ="id" , type = IdType.AUTO)
    private Integer id;
    private String typeid;
    private String devicename;
    private String norms;
    private String deviceno;
    private String describing;
    private String devicestatus;
    private String rentstatus;
    private String factoryid;
}
