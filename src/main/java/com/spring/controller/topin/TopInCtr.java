package com.spring.controller.topin;

import com.spring.dao.Banlance;
import com.spring.domain.RequestionParams.TopInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class TopInCtr {
    private Banlance banlance;

    @Autowired
    public void setBanlance(Banlance banlance) {
        this.banlance = banlance;
    }

    @PostMapping("/topIn")
    public ResponseEntity<?> topin(@RequestBody TopInRequest data) {
        System.out.println(data);
        int rows = 0;
        int money = 0;
        try {
            money = banlance.query(data.getUsername()).get(0).getBanlance();
            rows = banlance.topin(data.getUsername(), data.getAmount() + money);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Error", HttpStatusCode.valueOf(500));
        }
        if (rows == 1) {
            return new ResponseEntity<>("success", HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("Internal Error", HttpStatusCode.valueOf(500));
    }
}
