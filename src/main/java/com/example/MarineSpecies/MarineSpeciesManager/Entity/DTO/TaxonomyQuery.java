package com.example.MarineSpecies.MarineSpeciesManager.Entity.DTO;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 专用于Taxonomy查询的传参数实体类
 * 因为使用@RequestParam传List<String>会报错，只能用json了
 *
 * @author 2152189 汪林辉
 * @since 2023-10-17
 */
@Data
public class TaxonomyQuery {
    List<String> kingdomNameList;
    List<String> phylumNameList;
    List<String> classNameList;
    List<String> orderNameList;
    List<String> familyNameList;
    List<String> genusNameList;
}
