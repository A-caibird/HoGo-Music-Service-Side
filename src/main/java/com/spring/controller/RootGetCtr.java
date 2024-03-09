package com.spring.controller;

import com.spring.domain.RequestionParams.A;
import com.spring.service.authentication.email.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok打印
@RestController
public class RootGetCtr {
    @Autowired
    private EmailUtil mailService;

    @GetMapping("/json")
    public ResponseEntity<A> root() {
        log.info("info requestion RootGet");
        A a = new A();
        a.setStr("dasdasdf");
        //mailService.sendSimpleMail("lian.wk04201@foxmail.com","你好","验证码是2020");
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}

