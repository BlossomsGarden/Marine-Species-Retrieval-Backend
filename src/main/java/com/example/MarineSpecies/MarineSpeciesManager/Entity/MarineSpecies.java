package com.example.MarineSpecies.MarineSpeciesManager.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.MarineSpecies.Common.BaseEntity;
import lombok.Data;

/**
 * marine_species表实体类
 *
 * @author ************
 * @since 2023-10-17
 */
@Data
@TableName(value = "marine_species")
public class MarineSpecies extends BaseEntity {
    @TableId(type = IdType.AUTO)
    Integer id;
    String cnName;
    String enName;
    String latinName;
    String morphology;
    String habitat;
    String feature;
    Integer kingdomId;
    Integer phylumId;
    Integer classId;
    Integer orderId;
    Integer familyId;
    Integer genusId;
}
