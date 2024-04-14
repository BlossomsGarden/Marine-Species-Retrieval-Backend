package com.example.MarineSpecies.UserAndAdminLogin.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * admin 表实体类
 *
 * @author 2152189 汪林辉
 * @since 2024-03-23
 */
@Data
@TableName(value = "admin")
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String description;
    private String token;
    private String avatarUrl;
    private String email;
}
