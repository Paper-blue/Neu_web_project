package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    @TableId(value ="id" , type = IdType.AUTO)
    private Integer id;
    private String loginname;
    private String password;
    private String username;
    private String mobile;
    private String roleid;
}
