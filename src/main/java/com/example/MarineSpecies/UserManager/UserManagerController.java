package com.example.MarineSpecies.UserManager;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.MarineSpecies.Common.MyResponse;
import com.example.MarineSpecies.LoginManager.Entity.Admin;
import com.example.MarineSpecies.LoginManager.Entity.DTO.SignedIn;
import com.example.MarineSpecies.LoginManager.Entity.Organization;
import com.example.MarineSpecies.LoginManager.Entity.User;
import com.example.MarineSpecies.LoginManager.Mapper.AdminMapper;
import com.example.MarineSpecies.LoginManager.Mapper.OrganizationMapper;
import com.example.MarineSpecies.LoginManager.Mapper.UserMapper;
import com.example.MarineSpecies.SpeciesAndTaxonomyManager.Entity.MarineSpecies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息管理等方面的Controller类
 * 不能和Login方面的类混淆了
 *
 * @author ************
 * @since 2024-04-27
 */
@RestController
@RequestMapping("userManage")
@Slf4j
public class UserManagerController {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrganizationMapper organizationMapper;

    /**
     * 修改个人信息函数
     *
     * @param orgSignedIn session 参数，查找是谁发的
     * @param newSignedIn 传入的修改后的信息，更新到库里去，不要修改的字段是null
     */
    @PostMapping("editInfo")
    public MyResponse<String> editInfo(@RequestBody SignedIn newSignedIn , @SessionAttribute("signedIn") SignedIn orgSignedIn){
        if(orgSignedIn.getAdmin()==true){
            //修改管理员信息
            LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Admin::getId, orgSignedIn.getOperatorId());
            Admin thisAdmin=adminMapper.selectOne(lambdaQueryWrapper);

            if(newSignedIn.getName()!=null && newSignedIn.getName().length()>0){
                //昵称不允许为空
                thisAdmin.setName(newSignedIn.getName());
            }
            thisAdmin.setEmail(newSignedIn.getEmail());
            thisAdmin.setDescription(newSignedIn.getDescription());
            thisAdmin.setAvatarUrl(newSignedIn.getAvatarUrl());
            adminMapper.updateById(thisAdmin);

            //todo:管理员的sessionAttribute没有被更新

        }
        else {
            //修改用户信息
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getId, orgSignedIn.getOperatorId());
            User thisUser=userMapper.selectOne(lambdaQueryWrapper);

            if(newSignedIn.getName()!=null && newSignedIn.getName().length()>0) {
                //昵称不允许为空
                thisUser.setName(newSignedIn.getName());
            }
            thisUser.setEmail(newSignedIn.getEmail());
            thisUser.setDescription(newSignedIn.getDescription());
            thisUser.setAvatarUrl(newSignedIn.getAvatarUrl());
            userMapper.updateById(thisUser);

            //todo:用户的sessionAttribute没有被更新

        }
        return MyResponse.ok("已成功修改");
    }


    /**
     * 管理员需要获取所有用户进行管理
     * 这里没有过滤敏感信息，有必要的话可以单独写一个返回类
     */
    @GetMapping("allUser")
    public MyResponse<List<User>> getAllUser(
            @SessionAttribute("admin")Admin admin,
            @RequestParam(value = "keyword")String keyword,
            @RequestParam(value = "orgId")Integer orgId
    ){
         LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
         lambdaQueryWrapper
                 .like(User::getName, keyword)
                 .eq(User::getDeleted,false);
         if(orgId!=0){
             //如果是0也表示全部返回
             lambdaQueryWrapper.eq(User::getOrganizationId,orgId);
         }
        return MyResponse.ok(userMapper.selectList(lambdaQueryWrapper));
    }

    /**
     * 管理员封禁/解封用户，一个函数实现
     */
    @PostMapping("block")
    public MyResponse<Integer> blockUser(
            @SessionAttribute("admin")Admin admin,
            @RequestParam("userId") Integer userId
    ){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,userId);
        User user=userMapper.selectOne(wrapper);
        if(user.getBlocked()){
            user.setBlocked(false);
        }
        else {
            user.setBlocked(true);
        }
        return MyResponse.ok(userMapper.updateById(user));
    }

    /**
     * 管理员新建用户
     */
    @PostMapping("new")
    public MyResponse<Integer> newUser(
            @SessionAttribute("admin")Admin admin,
            @RequestBody User user
    ){
        return MyResponse.ok(userMapper.insert(user));
    }

    /**
     * 管理员删除用户
     */
    @PostMapping("delete")
    public MyResponse<Integer> deleteUser(
            @SessionAttribute("admin")Admin admin,
            @RequestParam("userId") Integer userId
    ){
        LambdaUpdateWrapper<User> wrapper=new LambdaUpdateWrapper<>();
        wrapper
                .eq(User::getId,userId)
                .set(User::getDeleted,true);
        return MyResponse.ok(userMapper.update(null,wrapper));
    }

    /**
     * 管理员查看所有组织
     */
    @GetMapping("allOrganization")
    public MyResponse<List<Organization>> getAllOrganization(@SessionAttribute("signedIn")SignedIn signedIn){
        return MyResponse.ok(organizationMapper.selectList(null));
    }
}
