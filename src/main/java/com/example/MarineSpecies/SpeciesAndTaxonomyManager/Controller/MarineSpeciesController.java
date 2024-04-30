package com.example.MarineSpecies.SpeciesAndTaxonomyManager.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.MarineSpecies.Common.MyResponse;
import com.example.MarineSpecies.LoginManager.Entity.DTO.SignedIn;
import com.example.MarineSpecies.LoginManager.Mapper.AdminMapper;
import com.example.MarineSpecies.LoginManager.Mapper.UserMapper;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.*;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.DTO.MarineSpeciesDTO;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.DTO.TaxonomyQuery;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * marine_species表控制类
 *
 * @author ************
 * @since 2024-04-23
 */
@RestController
@RequestMapping("marineSpecies")
@Slf4j
public class MarineSpeciesController {
    @Autowired
    private MarineSpeciesMapper marineSpeciesMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private KingdomMapper kingdomMapper;
    @Autowired
    private PhylumMapper phylumMapper;
    @Autowired
    private MyClassMapper myClassMapper;
    @Autowired
    private MyOrderMapper myOrderMapper;
    @Autowired
    private FamilyMapper familyMapper;
    @Autowired
    private GenusMapper genusMapper;

    /**
     * 分页获取海洋生物DTO信息，可以按名称搜索
     *
     * @param signedIn 自动注入已登录的操作者的session
     * @param pageNo 页号(从1开始)
     * @param pageSize 页大小
     * @pa
     * @return
     */
    @GetMapping("getAllByName")
    public MyResponse<IPage<MarineSpeciesDTO>> getFilteredSpecies(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "keyword",required = false) String keyword
    ) {
        // 创建分页对象
        Page<MarineSpecies> page = new Page<>(pageNo, pageSize);
        List<MarineSpecies> sourceRecords=new ArrayList<>();
        Long total = 0L;
        if(keyword==null){
            // 执行分页查询
            // LambdaQueryWrapper<MarineSpecies> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            // lambdaQueryWrapper.orderByDesc(MarineSpecies::getId);
            IPage<MarineSpecies> marineSpeciesIPage = marineSpeciesMapper.selectPage(page, null);
            // 复制数据
            sourceRecords = marineSpeciesIPage.getRecords();
            total=marineSpeciesIPage.getTotal();
        }
        else {
            sourceRecords = marineSpeciesMapper.fuzzyQueryByCondition(keyword,page);
            int size= sourceRecords.size();
            long ltotal=size;
            total=ltotal;
        }

        // 目标DTO列表
        List<MarineSpeciesDTO> targetRecords = new ArrayList<>();
        for(MarineSpecies marineSpecies:sourceRecords){
            MarineSpeciesDTO marineSpeciesDTO=new MarineSpeciesDTO();
            targetRecords.add(marineSpeciesDTO.encodeDTO(
                    marineSpecies,imageMapper,kingdomMapper,phylumMapper,
                    myClassMapper, myOrderMapper,familyMapper,genusMapper
            ));
        }
        //构建新的页，页内实体为DTO
        IPage<MarineSpeciesDTO> marineSpeciesDTOIPage = new Page<>(
                page.getCurrent(),
                page.getSize()
        );
        marineSpeciesDTOIPage.setRecords(targetRecords);
        marineSpeciesDTOIPage.setTotal(page.getTotal());
        return MyResponse.ok(marineSpeciesDTOIPage);
    }

    //HttpMessageNotReadableException: Required request body is missing
    //报此错误需要将这里的GetMapping改为PostMapping

    /**
     * 根据物种归属类目获得物种信息
     *
//     * @param signedIn      自动注入的sessionc参数
     * @param pageNo        分页页号
     * @param pageSize      分页大小
     * @param taxonomyQuery 查询参数
     * @return 信息列表
     */
    @PostMapping("getAllByTaxonomy")
    public MyResponse<IPage<MarineSpeciesDTO>> getTaxonomyFilteredSpecies(
//            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestBody TaxonomyQuery taxonomyQuery
    ) {
        // 构建查询条件
        LambdaQueryWrapper<MarineSpecies> wrapper = new LambdaQueryWrapper<>();
        List<String> kingdomNameList=taxonomyQuery.getKingdomNameList();
        List<String> phylumNameList=taxonomyQuery.getPhylumNameList();
        List<String> classNameList=taxonomyQuery.getClassNameList();
        List<String> orderNameList=taxonomyQuery.getOrderNameList();
        List<String> familyNameList= taxonomyQuery.getFamilyNameList();
        List<String> genusNameList=taxonomyQuery.getGenusNameList();
        if (kingdomNameList != null && !kingdomNameList.isEmpty()) {
            LambdaQueryWrapper<Kingdom> kingdomWrapper = new LambdaQueryWrapper<>();
            kingdomWrapper.in(Kingdom::getCnName, kingdomNameList);
            List<Kingdom> kingdomEntityList = kingdomMapper.selectList(kingdomWrapper);
            if (kingdomEntityList != null && !kingdomEntityList.isEmpty()) {
                List<Integer> kingdomIdList = kingdomEntityList.stream()
                        .map(Kingdom::getId)
                        .collect(Collectors.toList());
                wrapper.in(MarineSpecies::getKingdomId, kingdomIdList);
            } else {
                // 如果找不到对应的 Kingdom 记录，直接返回空列表或错误信息，当然这是不可能的
            }
        }
        if (phylumNameList != null && !phylumNameList.isEmpty()) {
            LambdaQueryWrapper<Phylum> phylumWrapper = new LambdaQueryWrapper<>();
            phylumWrapper.in(Phylum::getCnName, phylumNameList);
            List<Phylum> phylumEntityList = phylumMapper.selectList(phylumWrapper);
            if (phylumEntityList != null && !phylumEntityList.isEmpty()) {
                List<Integer> phylumIdList = phylumEntityList.stream()
                        .map(Phylum::getId)
                        .collect(Collectors.toList());
                wrapper.in(MarineSpecies::getPhylumId, phylumIdList);
            } else {
                // 如果找不到对应的 Kingdom 记录，直接返回空列表或错误信息，当然这是不可能的
            }
        }
        if (classNameList != null && !classNameList.isEmpty()) {
            LambdaQueryWrapper<MyClass> myClassWrapper = new LambdaQueryWrapper<>();
            myClassWrapper.in(MyClass::getCnName, classNameList);
            List<MyClass> myClassEntityList = myClassMapper.selectList(myClassWrapper);
            if (myClassEntityList != null && !myClassEntityList.isEmpty()) {
                List<Integer> myClassIdList = myClassEntityList.stream()
                        .map(MyClass::getId)
                        .collect(Collectors.toList());
                wrapper.in(MarineSpecies::getClassId, myClassIdList);
            } else {
                // 如果找不到对应的 Kingdom 记录，直接返回空列表或错误信息，当然这是不可能的
            }
        }
        if (orderNameList != null && !orderNameList.isEmpty()) {
            LambdaQueryWrapper<MyOrder> myOrderWrapper = new LambdaQueryWrapper<>();
            myOrderWrapper.in(MyOrder::getCnName, orderNameList);
            List<MyOrder> myOrderEntityList = myOrderMapper.selectList(myOrderWrapper);
            if (myOrderEntityList != null && !myOrderEntityList.isEmpty()) {
                List<Integer> myOrderIdList = myOrderEntityList.stream()
                        .map(MyOrder::getId)
                        .collect(Collectors.toList());
                wrapper.in(MarineSpecies::getOrderId, myOrderIdList);
            } else {
                // 如果找不到对应的 Kingdom 记录，直接返回空列表或错误信息，当然这是不可能的
            }
        }
        if (familyNameList != null && !familyNameList.isEmpty()) {
            LambdaQueryWrapper<Family> familyWrapper = new LambdaQueryWrapper<>();
            familyWrapper.in(Family::getCnName, familyNameList);
            List<Family> familyEntityList = familyMapper.selectList(familyWrapper);
            if (familyEntityList != null && !familyEntityList.isEmpty()) {
                List<Integer> familyIdList = familyEntityList.stream()
                        .map(Family::getId)
                        .collect(Collectors.toList());
                wrapper.in(MarineSpecies::getFamilyId, familyIdList);
            } else {
                // 如果找不到对应的 Kingdom 记录，直接返回空列表或错误信息，当然这是不可能的
            }
        }
        if (genusNameList != null && !genusNameList.isEmpty()) {
            LambdaQueryWrapper<Genus> genusWrapper = new LambdaQueryWrapper<>();
            genusWrapper.in(Genus::getCnName, genusNameList);
            List<Genus> genusEntityList = genusMapper.selectList(genusWrapper);
            if (genusEntityList != null && !genusEntityList.isEmpty()) {
                List<Integer> genusIdList = genusEntityList.stream()
                        .map(Genus::getId)
                        .collect(Collectors.toList());
                wrapper.in(MarineSpecies::getGenusId, genusIdList);
            } else {
                // 如果找不到对应的 Kingdom 记录，直接返回空列表或错误信息，当然这是不可能的
            }
        }

        // 查询符合条件的物种信息列表
        List<MarineSpecies> speciesList = marineSpeciesMapper.selectList(wrapper);
        Page<MarineSpecies> page = new Page<>(pageNo, pageSize);
        IPage<MarineSpecies> marineSpeciesIPage = marineSpeciesMapper.selectPage(page, wrapper);
        // 复制数据
        List<MarineSpecies> sourceRecords = marineSpeciesIPage.getRecords();
        // 目标DTO列表
        List<MarineSpeciesDTO> targetRecords = new ArrayList<>();
        for(MarineSpecies marineSpecies:sourceRecords){
            MarineSpeciesDTO marineSpeciesDTO=new MarineSpeciesDTO();
            targetRecords.add(marineSpeciesDTO.encodeDTO(
                    marineSpecies,imageMapper,kingdomMapper,phylumMapper,
                    myClassMapper, myOrderMapper,familyMapper,genusMapper
            ));
        }
        //构建新的页，页内实体为DTO
        IPage<MarineSpeciesDTO> marineSpeciesDTOIPage = new Page<>(
                page.getCurrent(),
                page.getSize()
        );
        marineSpeciesDTOIPage.setRecords(targetRecords);
        marineSpeciesDTOIPage.setTotal(page.getTotal());
        return MyResponse.ok(marineSpeciesDTOIPage);
    }

    /**
     * 获取所有的Taxonomy信息
     */
    @GetMapping("getTaxonomy")
    public MyResponse<TaxonomyQuery> getTaxonomyStringList(){
        TaxonomyQuery taxonomyQuery = new TaxonomyQuery();

        // 获取所有 Kingdom 的 cn_name 属性值
        List<Kingdom> kingdomList = kingdomMapper.selectList(null);
        List<String> kingdomNameList = kingdomList.stream()
                .map(Kingdom::getCnName)
                .collect(Collectors.toList());
        taxonomyQuery.setKingdomNameList(kingdomNameList);

        // 获取所有 Phylum 的 cn_name 属性值
        List<Phylum> phylumList = phylumMapper.selectList(null);
        List<String> phylumNameList = phylumList.stream()
                .map(Phylum::getCnName)
                .collect(Collectors.toList());
        taxonomyQuery.setPhylumNameList(phylumNameList);

        // 获取所有 MyClass 的 cn_name 属性值
        List<MyClass> myClassList = myClassMapper.selectList(null);
        List<String> classNameList = myClassList.stream()
                .map(MyClass::getCnName)
                .collect(Collectors.toList());
        taxonomyQuery.setClassNameList(classNameList);

        // 获取所有 MyOrder 的 cn_name 属性值
        List<MyOrder> myOrderList = myOrderMapper.selectList(null);
        List<String> orderNameList = myOrderList.stream()
                .map(MyOrder::getCnName)
                .collect(Collectors.toList());
        taxonomyQuery.setOrderNameList(orderNameList);

        // 获取所有 Family 的 cn_name 属性值
        List<Family> familyList = familyMapper.selectList(null);
        List<String> familyNameList = familyList.stream()
                .map(Family::getCnName)
                .collect(Collectors.toList());
        taxonomyQuery.setFamilyNameList(familyNameList);

        // 获取所有 Genus 的 cn_name 属性值
        List<Genus> genusList = genusMapper.selectList(null);
        List<String> genusNameList = genusList.stream()
                .map(Genus::getCnName)
                .collect(Collectors.toList());
        taxonomyQuery.setGenusNameList(genusNameList);

        return MyResponse.ok(taxonomyQuery);
    }


    /**
     * 新增海洋生物信息
     */
    @PostMapping("new")
    public MyResponse<String> newSpeciesInfo(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestBody MarineSpeciesDTO marineSpeciesDTO
    ){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("cn_name", marineSpeciesDTO.getCnName());
        //有同名则报错
        if(marineSpeciesMapper.selectByMap(map).size() > 0){
            return MyResponse.error("数据库中已有该数据！");
        }

        //先插入基本数据占id坑
        MarineSpecies marineSpecies=new MarineSpecies();
        BeanUtils.copyProperties(marineSpeciesDTO,marineSpecies);
        marineSpeciesMapper.insert(marineSpecies);
        marineSpecies.setId(marineSpeciesMapper.selectByMap(map).get(0).getId());

        //再插入image表
        for (String imageUrl : marineSpeciesDTO.getImageList()) {
            imageMapper.insert(new Image(imageUrl,marineSpecies.getId()));
        }

        //再插入各归属表并更新外键

        // Kingdom
        LambdaQueryWrapper<Kingdom> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Kingdom::getCnName, marineSpeciesDTO.getKingdomName());
        List<Kingdom> kingdomList = kingdomMapper.selectList(wrapper1);
        if (kingdomList.size() == 0) {
            // 没有则先插入
            kingdomMapper.insert(new Kingdom(marineSpeciesDTO.getKingdomName(), null));
            Kingdom kingdom2 = kingdomMapper.selectOne(wrapper1);
            marineSpecies.setKingdomId(kingdom2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setKingdomId(kingdomList.get(0).getId());
        }

        // Phylum
        LambdaQueryWrapper<Phylum> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Phylum::getCnName, marineSpeciesDTO.getPhylumName());
        List<Phylum> phylumList = phylumMapper.selectList(wrapper2);
        if (phylumList.size() == 0) {
            // 没有则先插入
            phylumMapper.insert(new Phylum(marineSpeciesDTO.getPhylumName(), null));
            Phylum phylum2 = phylumMapper.selectOne(wrapper2);
            marineSpecies.setPhylumId(phylum2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setPhylumId(phylumList.get(0).getId());
        }

        // MyClass
        LambdaQueryWrapper<MyClass> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.eq(MyClass::getCnName, marineSpeciesDTO.getClassName());
        List<MyClass> myClassList = myClassMapper.selectList(wrapper3);
        if (myClassList.size() == 0) {
            // 没有则先插入
            myClassMapper.insert(new MyClass(marineSpeciesDTO.getClassName(), null));
            MyClass myClass2 = myClassMapper.selectOne(wrapper3);
            marineSpecies.setClassId(myClass2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setClassId(myClassList.get(0).getId());
        }

        // MyOrder
        LambdaQueryWrapper<MyOrder> wrapper4 = new LambdaQueryWrapper<>();
        wrapper4.eq(MyOrder::getCnName, marineSpeciesDTO.getOrderName());
        List<MyOrder> myOrderList = myOrderMapper.selectList(wrapper4);
        if (myOrderList.size() == 0) {
            // 没有则先插入
            myOrderMapper.insert(new MyOrder(marineSpeciesDTO.getOrderName(), null));
            MyOrder myOrder2 = myOrderMapper.selectOne(wrapper4);
            marineSpecies.setOrderId(myOrder2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setOrderId(myOrderList.get(0).getId());
        }

        // Family
        LambdaQueryWrapper<Family> wrapper5 = new LambdaQueryWrapper<>();
        wrapper5.eq(Family::getCnName, marineSpeciesDTO.getFamilyName());
        List<Family> familyList = familyMapper.selectList(wrapper5);
        if (familyList.size() == 0) {
            // 没有则先插入
            familyMapper.insert(new Family(marineSpeciesDTO.getFamilyName(), null));
            Family family2 = familyMapper.selectOne(wrapper5);
            marineSpecies.setFamilyId(family2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setFamilyId(familyList.get(0).getId());
        }


        // genus
        LambdaQueryWrapper<Genus> wrapper6 = new LambdaQueryWrapper<>();
        wrapper6.eq(Genus::getCnName, marineSpeciesDTO.getGenusName());
        List<Genus> genusList = genusMapper.selectList(wrapper6);
        if (genusList.size()==0) {
            // 没有则先插入
            genusMapper.insert(new Genus(marineSpeciesDTO.getGenusName(), null));
            Genus genus2 = genusMapper.selectOne(wrapper6);
            marineSpecies.setGenusId(genus2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setGenusId(genusList.get(0).getId());
        }

        //更新基本数据对应的归属外键
        marineSpeciesMapper.updateById(marineSpecies);
        return MyResponse.ok("成功插入一条数据");
    }

    /**
     * 传入DTO修改生物信息
     */
    @PostMapping("editInfoViaDTO")
    public MyResponse<String> editSpeciesInfo(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestBody MarineSpeciesDTO marineSpeciesDTO
    ){
        //先根据DTO的id查询对应的marineSpecies
        LambdaQueryWrapper<MarineSpecies> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MarineSpecies::getId, marineSpeciesDTO.getId());
        MarineSpecies marineSpecies = marineSpeciesMapper.selectOne(wrapper);
        BeanUtils.copyProperties(marineSpeciesDTO,marineSpecies);
        //更新基本信息
        marineSpeciesMapper.updateById(marineSpecies);
        //更新图片列表
        editImage(signedIn,marineSpeciesDTO);
        //更新海洋生物类别
        editTaxonomy(signedIn,marineSpeciesDTO);
        return MyResponse.ok("获得返回值，但不意味着插入成功");
    }

    /**
     * 修改海洋生物基本信息
     */
    @PostMapping("editBasicInfo")
    public MyResponse<Integer> editSpeciesInfo(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestBody MarineSpecies marineSpecies
    ){
        return MyResponse.ok(marineSpeciesMapper.updateById(marineSpecies));
    }


    /**
     * 修改海洋生物关联图片
     */
    @PostMapping("editImage")
    public MyResponse<Integer> editImage(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestBody MarineSpeciesDTO marineSpeciesDTO
    ){
        Integer count=0;
        //首先将该物种全部置删除
        LambdaUpdateWrapper<Image> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Image::getMarineSpeciesId, marineSpeciesDTO.getId())
                .set(Image::getDeleted, true);
        imageMapper.update(null,updateWrapper);
        //然后再依次检查
        for(String url:marineSpeciesDTO.getImageList()){
            LambdaQueryWrapper<Image> wrapper = new LambdaQueryWrapper<>();
            wrapper
                    .eq(Image::getUrl, url)
                    .eq(Image::getMarineSpeciesId,marineSpeciesDTO.getId());
            Image image=imageMapper.selectOne(wrapper);
            if(image!=null){
                image.setDeleted(false);
                imageMapper.updateById(image);
            }
            else {
                imageMapper.insert(new Image(url,marineSpeciesDTO.getId()));
                count++;
            }
        }
        return MyResponse.ok(count,"成功插入的图片数");
    }


    /**
     * 修改海洋生物归属类别
     */
    @PostMapping("editTaxonomy")
    public MyResponse<String> editTaxonomy(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestBody MarineSpeciesDTO marineSpeciesDTO
    ){
        //先搜索是哪个物种吧
        LambdaQueryWrapper<MarineSpecies> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MarineSpecies::getId, marineSpeciesDTO.getId());
        MarineSpecies marineSpecies = marineSpeciesMapper.selectOne(wrapper);

        // Kingdom
        LambdaQueryWrapper<Kingdom> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Kingdom::getCnName, marineSpeciesDTO.getKingdomName());
        List<Kingdom> kingdomList = kingdomMapper.selectList(wrapper1);
        if (kingdomList.size() == 0) {
            // 没有则先插入
            kingdomMapper.insert(new Kingdom(marineSpeciesDTO.getKingdomName(), null));
            Kingdom kingdom2 = kingdomMapper.selectOne(wrapper1);
            marineSpecies.setKingdomId(kingdom2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setKingdomId(kingdomList.get(0).getId());
        }

        // Phylum
        LambdaQueryWrapper<Phylum> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Phylum::getCnName, marineSpeciesDTO.getPhylumName());
        List<Phylum> phylumList = phylumMapper.selectList(wrapper2);
        if (phylumList.size() == 0) {
            // 没有则先插入
            phylumMapper.insert(new Phylum(marineSpeciesDTO.getPhylumName(), null));
            Phylum phylum2 = phylumMapper.selectOne(wrapper2);
            marineSpecies.setPhylumId(phylum2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setPhylumId(phylumList.get(0).getId());
        }

        // MyClass
        LambdaQueryWrapper<MyClass> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.eq(MyClass::getCnName, marineSpeciesDTO.getClassName());
        List<MyClass> myClassList = myClassMapper.selectList(wrapper3);
        if (myClassList.size() == 0) {
            // 没有则先插入
            myClassMapper.insert(new MyClass(marineSpeciesDTO.getClassName(), null));
            MyClass myClass2 = myClassMapper.selectOne(wrapper3);
            marineSpecies.setClassId(myClass2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setClassId(myClassList.get(0).getId());
        }

        // MyOrder
        LambdaQueryWrapper<MyOrder> wrapper4 = new LambdaQueryWrapper<>();
        wrapper4.eq(MyOrder::getCnName, marineSpeciesDTO.getOrderName());
        List<MyOrder> myOrderList = myOrderMapper.selectList(wrapper4);
        if (myOrderList.size() == 0) {
            // 没有则先插入
            myOrderMapper.insert(new MyOrder(marineSpeciesDTO.getOrderName(), null));
            MyOrder myOrder2 = myOrderMapper.selectOne(wrapper4);
            marineSpecies.setOrderId(myOrder2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setOrderId(myOrderList.get(0).getId());
        }

        // Family
        LambdaQueryWrapper<Family> wrapper5 = new LambdaQueryWrapper<>();
        wrapper5.eq(Family::getCnName, marineSpeciesDTO.getFamilyName());
        List<Family> familyList = familyMapper.selectList(wrapper5);
        if (familyList.size() == 0) {
            // 没有则先插入
            familyMapper.insert(new Family(marineSpeciesDTO.getFamilyName(), null));
            Family family2 = familyMapper.selectOne(wrapper5);
            marineSpecies.setFamilyId(family2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setFamilyId(familyList.get(0).getId());
        }


        // genus
        LambdaQueryWrapper<Genus> wrapper6 = new LambdaQueryWrapper<>();
        wrapper6.eq(Genus::getCnName, marineSpeciesDTO.getGenusName());
        List<Genus> genusList = genusMapper.selectList(wrapper6);
        if (genusList.size()==0) {
            // 没有则先插入
            genusMapper.insert(new Genus(marineSpeciesDTO.getGenusName(), null));
            Genus genus2 = genusMapper.selectOne(wrapper6);
            marineSpecies.setGenusId(genus2.getId());
        } else {
            // 有则直接关联
            marineSpecies.setGenusId(genusList.get(0).getId());
        }

        //更新到数据表中
        marineSpeciesMapper.updateById(marineSpecies);
        return MyResponse.ok("已更新归属信息");
    }



    @Autowired
    private ContributorMapper contributorMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取物种信息的贡献者
     * 默认第一个是创建人
     */
    @GetMapping("getContributors")
    public MyResponse<List<SignedIn>> getContributors(
            @SessionAttribute("signedIn") SignedIn signedIn,
            @RequestParam("speciesId") Integer speciesId
    ){
        LambdaQueryWrapper<Contributor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Contributor::getMarineSpeciesId, speciesId);
        List<Contributor> contributorList = contributorMapper.selectList(wrapper);

        List<SignedIn> signedInList=new ArrayList<>();
        for (Contributor contributor:contributorList){
            SignedIn tmpSignedIn=new SignedIn();
            if(contributor.getAdmin()){
                tmpSignedIn.adminSignedIn(contributor.getOperatorId(),adminMapper);
            }
            else{
                tmpSignedIn.userSignedIn(signedIn.getOperatorId(),userMapper);
            }
            signedInList.add(tmpSignedIn);
        }
        return MyResponse.ok(signedInList);
    }


}
