package com.spring.controller;

import com.spring.dao.GetUserInfo;
import com.spring.dao.InsertUser;
import com.spring.domain.SignUpRequest;
import com.spring.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SignUpController {
    private GetUserInfo getUserInfo;
    private InsertUser insertUser;

    @Autowired
    public void setGetUserInfo(GetUserInfo getUserInfo) {
        this.getUserInfo = getUserInfo;
    }

    @Autowired
    public void setInsertUser(InsertUser insertUser) {
        this.insertUser = insertUser;
    }

    @PostMapping("/SignUp")
    public String signUp(@RequestBody SignUpRequest rb) {
        // 请求体参数
        String name = rb.getName();
        String password = rb.getPassword();
        String email = rb.getEmail();

        int len =  getUserInfo.getUserList(name).size();
        if (len> 0){
            return "users info exist";
        } else {
            int res = 0;
            try {
                res = insertUser.insertUser(name, password, email);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (res == 1)
                return "sign up success";
        }
        return "sign up fail";
    }
}
