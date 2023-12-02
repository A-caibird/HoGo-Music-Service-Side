package com.spring.controller;

import com.spring.domain.RequestionParams.A;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok打印
@RestController
public class RootGetCtr {
    @GetMapping("/json")
    public ResponseEntity<A> root() {
        log.info("info requestion RootGet");
        A a = new A();
        a.setStr("dasdasdf");
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}

