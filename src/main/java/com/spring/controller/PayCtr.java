package com.spring.controller;

import com.spring.domain.RequestionParams.PayRequest;
import com.spring.domain.SqlTable.Banlance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class PayCtr {
    private com.spring.dao.Banlance banlance;

    @Autowired
    public void setBanlance(com.spring.dao.Banlance banlance) {
        this.banlance = banlance;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payFunc(@RequestBody PayRequest Param) {
        System.out.println(Param.getPrice());

        Banlance temp;
        try {
            temp = banlance.query(Param.getName()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库错误");
            return new ResponseEntity<>("Server error", HttpStatusCode.valueOf(500));
        }
        if (temp.getBanlance() < Param.getPrice()) {
            System.out.println("Insufficient balance");
            return new ResponseEntity<>("Insufficient balance", HttpStatusCode.valueOf(400));
        }
        int rows = banlance.pay(Param.getName(), temp.getBanlance() - Param.getPrice());
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
