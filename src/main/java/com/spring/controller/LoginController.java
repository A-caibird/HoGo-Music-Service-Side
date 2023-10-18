package com.spring.controller;

import com.spring.dao.GetUserInfo;
import com.spring.domain.LoginRequest;
import com.spring.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    private GetUserInfo getUserInfo;
    @Autowired
    public void setGetUserInfo(GetUserInfo getUserInfo) {
        this.getUserInfo = getUserInfo;
    }
    @PostMapping("/LogIn")
    public String login(@RequestBody LoginRequest user) {
        // 请求体参数
        String name = user.getName();
        String password = user.getPassword();
        System.out.println(name + " " + password);
        // 从数据库中获取用户信息
        UserInfo a = getUserInfo.getUserList(name).get(0);
        System.out.println(a.getName() + " " + a.getPassword());
        // 判断用户信息是否正确
        if (a.getName().equals(name) && a.getPassword().equals(password)) {
            return "success";
        } else if (!a.getName().equals(name) && a.getPassword().equals(password)) {
            return "no this user";
        } else
            return "fail";
    }
}
