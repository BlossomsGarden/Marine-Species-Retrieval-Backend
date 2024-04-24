package com.example.MarineSpecies.MarineSpeciesManager.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.MarineSpecies.MarineSpeciesManager.Entity.MarineSpecies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MarineSpeciesMapper extends BaseMapper<MarineSpecies> {

    @Select("SELECT * FROM marine_species " +
            "WHERE " +
            "cn_name LIKE CONCAT('%', #{keyword}, '%') OR " +
            "en_name LIKE CONCAT('%', #{keyword}, '%') OR " +
            "latin_name LIKE CONCAT('%', #{keyword}, '%')" +
            " ")
    List<MarineSpecies> fuzzyQueryByCondition(String keyword, Page<MarineSpecies> page);
}
