package com.example.MarineSpecies.LoginManager.Entity.DTO;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.MarineSpecies.Common.BaseEntity;
import com.example.MarineSpecies.LoginManager.Entity.Admin;
import com.example.MarineSpecies.LoginManager.Entity.User;
import com.example.MarineSpecies.LoginManager.Mapper.AdminMapper;
import com.example.MarineSpecies.LoginManager.Mapper.UserMapper;
import lombok.Data;

/**
 * 非数据表实体类
 * 只是为了将User和Admin强行划为一类，因此创建了一个“已登录”类
 * 因为很多接口都是公用的，只有少数管理员特权，因此为了方便得搞一个更高的session
 *
 * @author ************
 * @since 2023-03-27
 */
@Data
public class SignedIn extends BaseEntity {
    private Integer operatorId;
    private Boolean admin;
    private Integer organizationId;
    private String name;
    private String description;
    private String email;
    private String avatarUrl;

    /**
     * 以下是构造函数，传入2个参数可以注入相关信息
     */
    public void adminSignedIn(Integer _operatorId, AdminMapper _adminMapper){
        operatorId=_operatorId;
        admin=true;

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getId, _operatorId);
        Admin thisAdmin = _adminMapper.selectOne(lambdaQueryWrapper);

        name=thisAdmin.getName();
        avatarUrl=thisAdmin.getAvatarUrl();
        description= thisAdmin.getDescription();
        email= thisAdmin.getEmail();
        organizationId=1;   //管理员就是第一个组织
        createTime=thisAdmin.getCreateTime();
        updateTime=thisAdmin.getUpdateTime();
    }
    public void userSignedIn(Integer _operatorId, UserMapper _userMapper){
        operatorId=_operatorId;
        admin=false;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId, _operatorId);
        User thisUser = _userMapper.selectOne(lambdaQueryWrapper);

        name=thisUser.getName();
        avatarUrl=thisUser.getAvatarUrl();
        description= thisUser.getDescription();
        email= thisUser.getEmail();
        organizationId=thisUser.getOrganizationId();
        createTime=thisUser.getCreateTime();
        updateTime=thisUser.getUpdateTime();
    }
}