package com.example.MarineSpecies.LoginManager.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.MarineSpecies.LoginManager.Entity.MenuPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuPermissionMapper extends BaseMapper<MenuPermission> {

}
