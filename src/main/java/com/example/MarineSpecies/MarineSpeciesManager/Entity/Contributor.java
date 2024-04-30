package com.example.MarineSpecies.MarineSpeciesManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * contributor表实体类
 *
 * @author ************
 * @since 2024-04-23
 */
@Data
@TableName(value = "contributor")
public class Contributor {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer operatorId;
    Boolean admin;
    Integer marineSpeciesId;
}
