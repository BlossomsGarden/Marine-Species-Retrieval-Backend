package com.example.MarineSpecies.MarineSpeciesManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * genus表实体类
 *
 * @author ************
 * @since 2024-04-23
 */
@Data
@TableName(value = "genus")
public class Genus {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cnName;
    String enName;
    public Genus(String _cnName,String _enName){
        cnName=_cnName;
        enName=_enName;
    }
}
