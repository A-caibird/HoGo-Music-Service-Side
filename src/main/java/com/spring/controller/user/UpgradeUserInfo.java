package com.spring.controller.user;

import com.spring.service.authentication.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UpgradeUserInfo {
    @PostMapping("/upgradeUserInfo")
    public ResponseEntity<?> handler(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("username") String username) {
        // 解析请求头获取 Token
        String token = authorizationHeader.replace("Bearer ", "");
        log.info("需要修改信息的用户的token是:  " + token);
        VerificationCodeGenerator.deleCode(username);
        if (VerificationCodeGenerator.getVerificationCode(username) == null) {
            log.info("删除邮箱验证码成功");
        }
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
