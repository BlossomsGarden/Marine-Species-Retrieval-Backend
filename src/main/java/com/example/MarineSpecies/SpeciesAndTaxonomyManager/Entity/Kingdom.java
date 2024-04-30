package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * kingdom表实体类
 *
 * @author ************
 * @since 2024-04-23
 */
@Data
@TableName(value = "kingdom")
public class Kingdom {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cnName;
    String enName;

    public Kingdom(String _cnName,String _enName){
        cnName=_cnName;
        enName=_enName;
    }
}
