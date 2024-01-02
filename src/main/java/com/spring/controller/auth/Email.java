package com.spring.controller.auth;

import com.spring.service.authentication.VerificationCodeGenerator;
import com.spring.service.authentication.email.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class Email {
    private EmailUtil mailService;

    //private VerificationCodeGenerator coder;
    //
    //@Autowired
    //public void setCoder(VerificationCodeGenerator coder) {
    //    this.coder = coder;
    //}

    @Autowired
    public void setMailService(EmailUtil mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/authmail")
    public ResponseEntity<?> handler(@RequestParam("username") String username, @RequestParam("email") String email) {
        String code = VerificationCodeGenerator.generateVerificationCode();
        VerificationCodeGenerator.saveVerificationCode(username, code);
        try {
            mailService.sendSimpleMail(email, "HoGo Music:用户个人信息更改身份验证", "验证码:" + code + "  ,验证码将会在15分钟后失效,请及时完成身份验证!  注意:验证码泄漏可能会导致账号被盗,请即使删除!");
        } catch (Exception e) {
            return new ResponseEntity<>("false", HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
