package com.spring;

import com.spring.service.authentication.email.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private EmailUtil mailService;

    @Test
    void contextLoads() {
        mailService.sendSimpleMail("xxx@qq.com", "主题：你好普通邮件", "内容：第一封邮件");
    }

}
