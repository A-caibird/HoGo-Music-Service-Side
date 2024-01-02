package com.spring.controller.user;

import com.spring.dao.Users;
import com.spring.service.authentication.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UpgradeUserInfo {
    private Users users;

    @Autowired
    public void setUsers(Users users) {
        this.users = users;
    }

    @PostMapping("/upgradeUserInfo")
    public ResponseEntity<?> handler(@RequestHeader("Authorization") String authorizationHeader,@RequestBody Map<String,String> map) {
        // 解析请求头获取 Token
        String token = authorizationHeader.replace("Bearer ", "");
        String username=map.get("username");
        String oldName=map.get("oldName");
        String password=map.get("password");
        String email=map.get("email");

        if (!token.equals(VerificationCodeGenerator.getVerificationCode(username))) {
            log.info("用户-" + username + " :身份验证错误");
            return new ResponseEntity<>("auth error", HttpStatusCode.valueOf(403));
        }

        VerificationCodeGenerator.deleCode(username);
        if (VerificationCodeGenerator.getVerificationCode(username) == null) {
            log.info("删除用户" + username + "邮箱验证码成功");
        }

        try {
            users.upgradeInfo(oldName, username, password, email);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server internal error", HttpStatusCode.valueOf(500));
        }

        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
