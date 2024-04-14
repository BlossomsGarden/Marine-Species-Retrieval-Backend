package com.example.MarineSpecies.UserAndAdminLogin.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.MarineSpecies.UserAndAdminLogin.Entity.User;
import com.example.MarineSpecies.UserAndAdminLogin.Mapper.UserMapper;
import com.example.MarineSpecies.UserAndAdminLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

}
