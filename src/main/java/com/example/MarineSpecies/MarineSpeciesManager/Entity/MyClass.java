package com.example.MarineSpecies.MarineSpeciesManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * my_class表实体类
 * 使用MyClass是为了避免关键字冲突
 *
 * @author ************
 * @since 2024-04-23
 */
@Data
@TableName(value = "my_class")
public class MyClass {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cnName;
    String enName;
    public MyClass(String _cnName,String _enName){
        cnName=_cnName;
        enName=_enName;
    }
}
