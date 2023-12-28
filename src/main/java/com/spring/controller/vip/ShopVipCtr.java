package com.spring.controller.vip;

import com.spring.dao.Vip;
import com.spring.domain.RequestionParams.ShopVipRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class ShopVipCtr {
    private Vip vip;

    @Autowired
    public void setVip(Vip vip) {
        this.vip = vip;
    }

    @PostMapping("/payVip")
    public ResponseEntity<String> payVip(@RequestBody ShopVipRequest a) {
        String name = a.getUsername();
        String date1 = a.getStartDate();
        String date2 = a.getEndDate();
        // 定义转化时间格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date mysqlStartDate = null, mysqlEndDate = null;
        try {
            // java工具类字符串
            java.util.Date utilDate1, utilDate2;
            utilDate1 = formatter.parse(date1);
            utilDate2 = formatter.parse(date2);

            //转化为mysql时间
            mysqlEndDate = new Date(utilDate2.getTime());
            mysqlStartDate = new Date(utilDate1.getTime());
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>("时间格式转化错误", HttpStatusCode.valueOf(500));
        }
        int rows = vip.updateVipStatus(name, 1, mysqlStartDate, mysqlEndDate);
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
