package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.MyOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MyOrderMapper extends BaseMapper<MyOrder> {
}
