package com.example.MarineSpecies.MarineSpeciesManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * phylum表实体类
 *
 * @author 2152189 汪林辉
 * @since 2024-04-23
 */
@Data
@TableName(value = "phylum")
public class Phylum {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cnName;
    String enName;
    public Phylum(String _cnName,String _enName){
        cnName=_cnName;
        enName=_enName;
    }
}
