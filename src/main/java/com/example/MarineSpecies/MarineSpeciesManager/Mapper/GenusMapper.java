package com.example.MarineSpecies.MarineSpeciesManager.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.MarineSpeciesManager.Entity.Genus;
import com.example.MarineSpecies.MarineSpeciesManager.Entity.MarineSpecies;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GenusMapper  extends BaseMapper<Genus> {
}
