package com.example.MarineSpecies.EventTracking;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.MarineSpecies.Common.MyResponse;
import com.example.MarineSpecies.LoginManager.Entity.DTO.SignedIn;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.Image;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.MarineSpecies;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper.ImageMapper;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper.MarineSpeciesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 获取数据埋点的控制类（如一共多少个物种多少张图片）
 *
 * @author ************
 * @since 2023-10-17
 */
public class EventTrackingController {
    @Autowired
    MarineSpeciesMapper marineSpeciesMapper;
    @Autowired
    ImageMapper imageMapper;

    /**
     * 类似埋点：总物种数
     */
    @GetMapping("totalSpecies")
    public MyResponse<Integer> totalSpecies(@SessionAttribute("signedIn") SignedIn signedIn){
        LambdaQueryWrapper<MarineSpecies> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(MarineSpecies::getDeleted,false);
        return MyResponse.ok(marineSpeciesMapper.selectList(wrapper).size());
    }

    /**
     * 类似埋点：总图片数
     */
    @GetMapping("totalImage")
    public MyResponse<Integer> totalImage(@SessionAttribute("signedIn")SignedIn signedIn){
        LambdaQueryWrapper<Image> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Image::getDeleted,false);
        return MyResponse.ok(imageMapper.selectList(wrapper).size());
    }

    /**
     * 总访问量(弃置)
     */

    /**
     * 生物信息类别分布(弃置)
     */

    /**
     * 搜索热度(弃置)
     */
}
