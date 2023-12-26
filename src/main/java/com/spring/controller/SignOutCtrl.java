package com.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class SignOutCtrl {


    @PostMapping("/signout")
    public ResponseEntity<?> signOut(@RequestBody Map<String, Object> param, HttpServletRequest request){
        System.out.println(param);
        HttpSession session = request.getSession(); // 获取当前会话，如果不存在则返回null
        if (session != null) {
            String name = (String) session.getAttribute("name"); // 获取名为"name"的属性值

            // 测试同一个用户从浏览器发起请求是不是同一个sesssion
//            System.out.println(name);
//            System.out.println(session.getId());
            session.invalidate(); // 使会话无效
        }
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
