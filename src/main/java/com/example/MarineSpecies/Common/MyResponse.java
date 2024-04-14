package com.example.MarineSpecies.Common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.MarineSpecies.UserAndAdminLogin.Entity.Admin;
import com.example.MarineSpecies.UserAndAdminLogin.Entity.User;
import com.example.MarineSpecies.UserAndAdminLogin.Mapper.AdminMapper;
import com.example.MarineSpecies.UserAndAdminLogin.Mapper.UserMapper;
import lombok.Data;

/**
 * 封装好的返回数据
 *
 * @author 2152189 汪林辉
 * @since 2024-03-23
 */
@Data
public class MyResponse<T> {
    private T data;
    private boolean success;
    private String msg;

    public static <T> MyResponse<T> ok() {
        MyResponse<T> myResponse = new MyResponse<>();
        myResponse.setSuccess(true);
        myResponse.setData(null);
        return myResponse;
    }

    public static <T> MyResponse<T> ok(T data) {
        MyResponse<T> myResponse = new MyResponse<>();
        myResponse.setSuccess(true);
        myResponse.setData(data);
        return myResponse;
    }

    public static <T> MyResponse<T> ok(T data, String msg) {
        MyResponse<T> myResponse = new MyResponse<>();
        myResponse.setSuccess(true);
        myResponse.setMsg(msg);
        myResponse.setData(data);
        return myResponse;
    }

    public static <T> MyResponse<T> error(T data) {
        MyResponse<T> myResponse = new MyResponse<>();
        myResponse.setSuccess(false);
        myResponse.setData(data);
        return myResponse;
    }

    public static <T> MyResponse<T> error(T data,String msg) {
        MyResponse<T> myResponse = new MyResponse<>();
        myResponse.setSuccess(false);
        myResponse.setData(data);
        myResponse.setMsg(msg);
        return myResponse;
    }

    public static MyResponse<String> error(String msg) {
        MyResponse<String> myResponse = new MyResponse<>();
        myResponse.setSuccess(false);
        myResponse.setMsg(msg);
        return myResponse;
    }
}
