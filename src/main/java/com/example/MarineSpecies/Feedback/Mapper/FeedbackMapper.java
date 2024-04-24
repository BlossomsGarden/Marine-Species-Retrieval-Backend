package com.example.MarineSpecies.Feedback.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.Feedback.Entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//不加这两句马上Error Create Bean
@Mapper
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {
}
