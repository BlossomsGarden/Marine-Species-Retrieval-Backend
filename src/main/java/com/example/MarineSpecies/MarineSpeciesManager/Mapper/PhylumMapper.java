package com.example.MarineSpecies.MarineSpeciesManager.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.MarineSpeciesManager.Entity.Phylum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PhylumMapper extends BaseMapper<Phylum> {
}
