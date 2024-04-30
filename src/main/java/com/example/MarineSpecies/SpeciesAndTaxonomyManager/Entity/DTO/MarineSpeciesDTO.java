package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.DTO;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.MarineSpecies.Common.BaseEntity;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.Image;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.MarineSpecies;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO,除了MarineSpecies基本信息外，再注入image、kingdom、……
 *
 * @author ************
 * @since 2023-10-17
 */
@Data
public class MarineSpeciesDTO extends BaseEntity {
    Integer id;
    String cnName;
    String enName;
    String latinName;
    String morphology;
    String habitat;
    String feature;
    String kingdomName;
    String phylumName;
    String className;
    String orderName;
    String familyName;
    String genusName;
    List<String> imageList;

    public MarineSpeciesDTO encodeDTO(
            MarineSpecies marineSpecies,
            ImageMapper imageMapper,
            KingdomMapper kingdomMapper,
            PhylumMapper phylumMapper,
            MyClassMapper myClassMapper,
            MyOrderMapper myOrderMapper,
            FamilyMapper familyMapper,
            GenusMapper genusMapper
    ){
        MarineSpeciesDTO marineSpeciesDTO=new MarineSpeciesDTO();

        //赋值基本信息
        BeanUtils.copyProperties(marineSpecies,marineSpeciesDTO);

        //查询该物种的所有图片
        List<String> imageStringList=new ArrayList<>();
        LambdaQueryWrapper<Image> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Image::getMarineSpeciesId,marineSpecies.getId())
                .eq(Image::getDeleted,false);
        List<Image> tmpImageList = imageMapper.selectList(lambdaQueryWrapper);
        for (Image obj : tmpImageList) {
            imageStringList.add(obj.getUrl());
        }
        marineSpeciesDTO.setImageList(imageStringList);

        try{
            //注入该物种的界门纲目科属种
            marineSpeciesDTO.setKingdomName(
                    kingdomMapper.selectById(marineSpecies.getKingdomId()).getCnName()
            );
            marineSpeciesDTO.setPhylumName(
                    phylumMapper.selectById(marineSpecies.getPhylumId()).getCnName()
            );
            marineSpeciesDTO.setClassName(
                    myClassMapper.selectById(marineSpecies.getClassId()).getCnName()
            );
            marineSpeciesDTO.setOrderName(
                    myOrderMapper.selectById(marineSpecies.getOrderId()).getCnName()
            );
            marineSpeciesDTO.setFamilyName(
                    familyMapper.selectById(marineSpecies.getFamilyId()).getCnName()
            );
            marineSpeciesDTO.setGenusName(
                    genusMapper.selectById(marineSpecies.getGenusId()).getCnName()
            );
        }
        catch (Exception e){
            System.out.println("错误");
            System.out.println(e);
            System.out.println("看看id");
            System.out.println(marineSpecies.getId());
            System.out.println("看看中文名");
            System.out.println(marineSpecies.getCnName());
        }

        return marineSpeciesDTO;
    }
}
