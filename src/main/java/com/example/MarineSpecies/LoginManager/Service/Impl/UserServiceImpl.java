package com.example.MarineSpecies.LoginManager.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.MarineSpecies.LoginManager.Entity.User;
import com.example.MarineSpecies.LoginManager.Mapper.UserMapper;
import com.example.MarineSpecies.LoginManager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

}
