package com.spring.controller;

import com.spring.dao.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GetVipStatusCtr {
    private Vip vip;

    @Autowired
    public void setVip(Vip vip){
        this.vip = vip;
    }
    @GetMapping("/getVipStatus")
    // 返回值是一个匿名对象
    public ResponseEntity<?> query(@RequestParam(value = "username", required = true) String name) {
        int status = 0;
        try {
            status = vip.getVipStatus(name);
        } catch (Exception err) {
            err.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatusCode.valueOf(500));
        }
        if (status == 1) {
            return new ResponseEntity<>(new Object() {
                public   Boolean isVip = true;
            }, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(new Object() {
            public Boolean isVip = false;
        }, HttpStatusCode.valueOf(200));
    }
}
