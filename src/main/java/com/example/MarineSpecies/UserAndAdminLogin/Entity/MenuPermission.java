package com.example.MarineSpecies.UserAndAdminLogin.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * menu_permission 表实体类
 *
 * @author ************
 * @since 2023-03-23
 */
@Data
@TableName(value = "menu_permission")
public class MenuPermission {
    private Integer routeId;

    private String routeName;

    private String description;
    private Integer operatorId;
    private Boolean admin;
}
