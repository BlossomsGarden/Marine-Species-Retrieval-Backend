package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.Genus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GenusMapper  extends BaseMapper<Genus> {
}
