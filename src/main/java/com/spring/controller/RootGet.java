package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootGet {
    @GetMapping("/")
    public String root() {
        return "Hello World!";
    }
}
