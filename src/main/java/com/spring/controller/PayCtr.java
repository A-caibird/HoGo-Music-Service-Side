package com.spring.controller;

import com.spring.dao.Pay;
import com.spring.dao.QueryBanlance;
import com.spring.domain.RequestionParams.PayRequest;
import com.spring.domain.SqlTable.Banlance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PayCtr {
    private Pay pay;
    private QueryBanlance q;
    @Autowired
    public void setPay(Pay pay) {
        this.pay = pay;
    }
    @Autowired
    public void setQ(QueryBanlance q){
        this.q = q;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payFunc(@RequestBody PayRequest Param) {
        System.out.println(Param.getPrice());

        Banlance temp;
        try{
           temp =  q.query(Param.getName()).get(0);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库错误");
            return new ResponseEntity<>("Server error",HttpStatusCode.valueOf(500));
        }
        if(temp.getBanlance()<Param.getPrice()){
            System.out.println("Insufficient balance");
            return new ResponseEntity<>("Insufficient balance",HttpStatusCode.valueOf(400));
        }
        int rows = pay.pay(Param.getName(),temp.getBanlance()-Param.getPrice());
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
