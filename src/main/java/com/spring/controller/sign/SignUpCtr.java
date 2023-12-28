package com.spring.controller.sign;

import com.spring.dao.*;
import com.spring.domain.RequestionParams.SignUpRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class SignUpCtr {
    private Vip vip;
    private Banlance banlance;

    private Users users;

    @Autowired
    public void setUsers(Users users) {
        this.users = users;
    }

    @Autowired
    public void setVip(Vip vip) {
        this.vip = vip;
    }

    @PostMapping("/SignUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest rb) {
        // 请求体参数
        String name = rb.getName();
        String password = rb.getPassword();
        String email = rb.getEmail();

        int len = users.getUserInfo(name).size();
        if (len > 0) {
            return new ResponseEntity<>("users info exist", HttpStatus.CONFLICT);
        } else {
            int res = 0;
            try {
                res = users.insertUser(name, password, email);
                res += banlance.initBanlance(name, 0);
                res += vip.initVipStatus(name, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (res == 3) return new ResponseEntity<>("sign up success", HttpStatus.OK);
        }
        return new ResponseEntity<>("sign up fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
