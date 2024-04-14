package com.example.MarineSpecies.UserAndAdminLogin.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.MarineSpecies.UserAndAdminLogin.Entity.User;
import com.example.MarineSpecies.UserAndAdminLogin.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
}
