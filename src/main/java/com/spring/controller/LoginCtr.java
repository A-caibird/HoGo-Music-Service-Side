package com.spring.controller;

import com.spring.dao.Users;
import com.spring.domain.RequestionParams.LoginRequest;
import com.spring.domain.SqlTable.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class LoginCtr {
    private Users users;
    @Autowired
    public void setUsers(Users users){
        this.users = users;
    }

    @PostMapping("/LogIn")
    public ResponseEntity<String> login(@RequestBody LoginRequest user, HttpServletRequest request) {
        // 请求体参数
        String name = user.getName();
        String password = user.getPassword();
        System.out.println(name + " " + password);
        UserInfo a = new UserInfo();
        HttpSession session = request.getSession();
        // 从数据库中获取用户信息
        // 1.没有该用户
        try {
            a = users.getUserInfo(name).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no this user");
            return new ResponseEntity<>("no this user", HttpStatus.NOT_FOUND);
        }
        System.out.println(a.getName() + " " + a.getPassword());
        if(a.getActive() != 1){
            return new ResponseEntity<>("deactive",HttpStatus.FORBIDDEN);
        }
        // 2.有用户,判断用户信息是否正确
        if (a.getName().equals(name) && a.getPassword().equals(password)) {

            // 创建session
//            session.setAttribute("name",a.getName());
            return new ResponseEntity<>("success",HttpStatus.OK);
        } else
            return new ResponseEntity<>("Fail", HttpStatus.UNAUTHORIZED);
    }
}
