package com.spring.controller.user;

import com.spring.dao.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class GetUserInfo {
    private Users users;

    @Autowired
    public void setUsers(Users users) {
        this.users = users;
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> handler(@RequestParam("name") String username) {
        try {
            return new ResponseEntity<>(users.getUserInfo(username), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户信息的时候服务器产生错误!");
            return new ResponseEntity<>("false", HttpStatusCode.valueOf(500));
        }
    }
}
