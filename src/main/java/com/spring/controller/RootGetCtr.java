package com.spring.controller;

import com.spring.domain.RequestionParams.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootGetCtr {
    @GetMapping("/json")
    public ResponseEntity<A> root() {
        A a = new A();
        a.setStr("dasdasdf");
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}

