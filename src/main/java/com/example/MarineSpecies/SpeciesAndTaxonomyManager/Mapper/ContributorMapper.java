package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.Contributor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ContributorMapper extends BaseMapper<Contributor> {

}
