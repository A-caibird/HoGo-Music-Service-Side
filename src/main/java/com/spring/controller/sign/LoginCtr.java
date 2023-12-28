package com.spring.controller.sign;

import com.spring.dao.Users;
import com.spring.domain.RequestionParams.LoginRequest;
import com.spring.domain.SqlTable.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
//前后端分离项目背景下，跨域访问及一致性session问题（是否同一用户）。
//ps：以前做的项目都是前、后端部署在一个tomcat容器中，不会涉及到跨域访问以及一致性session问题。随着前后端分离架构的流行，前、后端部署在不同服务器等都会涉及到跨域等问题。
@Slf4j
public class LoginCtr {
    private Users users;

    @Autowired
    public void setUsers(Users users) {
        this.users = users;
    }

    @PostMapping("/LogIn")
    public ResponseEntity<String> login(@RequestBody LoginRequest user, HttpServletRequest request) {

        // false:获取当前会话，如果不存在则返回null,true:为空创建一个新的session,否则获取当前的session
        HttpSession session = request.getSession();

        // 请求体参数
        String name = user.getName();
        String password = user.getPassword();
        UserInfo a;

        // 从数据库中获取用户信息
        // 1.没有该用户
        try {
            a = users.getUserInfo(name).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no this user");
            return new ResponseEntity<>("no this user", HttpStatus.NOT_FOUND);
        }

        // 2.用户是否被禁用
        if (a.getActive() != 1) {
            return new ResponseEntity<>("deactive", HttpStatus.FORBIDDEN);
        }

        // 3.有用户,判断用户信息是否正确
        if (a.getName().equals(name) && a.getPassword().equals(password)) {

            log.info("为用户:" + name + "创建httpSession={}" + session.getId());

            // 设置session属性
            session.setAttribute("name", a.getName());

            return new ResponseEntity<>("success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Fail", HttpStatus.UNAUTHORIZED);
    }
}
