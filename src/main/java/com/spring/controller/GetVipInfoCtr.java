package com.spring.controller;

import com.spring.dao.Vip;
import com.spring.domain.SqlTable.VipTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class GetVipInfoCtr {
    private Vip vip;

    @Autowired
    public void setVip(Vip vip) {
        this.vip = vip;
    }

    @GetMapping("/getVipInfo")
    // 返回值是一个匿名对象
    public ResponseEntity<?> query(@RequestParam(value = "username", required = true) String name) {
        VipTable info;
        try {
            info = vip.getVipAllInfo(name);
        } catch (Exception err) {
            err.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(info, HttpStatusCode.valueOf(200));
    }
}
