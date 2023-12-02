package com.spring.controller;

import com.spring.dao.GetUserVipStatus;
import com.spring.domain.RequestionParams.GetVipStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class GetVipStatusCtr {
    private GetUserVipStatus gt;

    @Autowired
    public void setGt(GetUserVipStatus gt) {
        this.gt = gt;
    }

    @GetMapping("/getVipStatus")
    // 返回值是一个匿名对象
    public ResponseEntity<?> query(@RequestParam(value = "username", required = true) String name) {
        int status = 0;
        try {
            status = gt.get(name);
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
