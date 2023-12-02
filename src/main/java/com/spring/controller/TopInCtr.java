package com.spring.controller;

import com.spring.dao.QueryBanlance;
import com.spring.dao.TopInBanlance;
import com.spring.domain.RequestionParams.TopInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class TopInCtr {
    private TopInBanlance topIn;
    private QueryBanlance qb;

    @Autowired
    public void setTopIn(TopInBanlance topIn) {
        this.topIn = topIn;
    }

    @Autowired
    public void setQb(QueryBanlance qb) {
        this.qb = qb;
    }

    @PostMapping("/topIn")
    public ResponseEntity<?> topin(@RequestBody TopInRequest data) {
        System.out.println(data);
        int rows = 0;
        int banlance = 0;
        try {
            banlance = qb.query(data.getUsername()).get(0).getBanlance();
            rows = topIn.topin(data.getUsername(), data.getAmount()+banlance);
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
