package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("devicetype")
@Data
public class DeviceType {
    @TableId(value ="id" , type = IdType.AUTO)
    private String id;
    private String typename;
}
