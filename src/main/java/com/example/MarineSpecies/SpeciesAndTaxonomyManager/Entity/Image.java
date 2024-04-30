package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * image表实体类
 *
 * @author ************
 * @since 2024-04-23
 */
@Data
@TableName(value = "image")
public class Image {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer marineSpeciesId;
    String url;
    Boolean deleted;

    public Image(String _url,Integer _sid){
        url=_url;
        marineSpeciesId=_sid;
    }
}
