package com.example.MarineSpecies.LoginManager.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * organization 表实体类
 *
 * @author ************
 * @since 2024-04-27
 */
@Data
@TableName(value = "organization")
public class Organization {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
