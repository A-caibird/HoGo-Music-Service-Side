package com.spring.controller;

import com.spring.dao.GetSpecificUserInfo;
import com.spring.dao.InitBanlance;
import com.spring.dao.InitVipStatus;
import com.spring.dao.InsertUser;
import com.spring.domain.RequestionParams.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SignUpCtr {
    private GetSpecificUserInfo getSpecificUserInfo;
    private InsertUser insertUser;
    private InitVipStatus initvip;
    private InitBanlance initbanlance;
    @Autowired
    public void setInitbanlance(InitBanlance initbanlance){
        this.initbanlance = initbanlance;
    }
    @Autowired
    public void setInitvip(InitVipStatus initvip){
        this.initvip = initvip;
    }

    @Autowired
    public void setGetUserInfo(GetSpecificUserInfo getSpecificUserInfo) {
        this.getSpecificUserInfo = getSpecificUserInfo;
    }

    @Autowired
    public void setInsertUser(InsertUser insertUser) {
        this.insertUser = insertUser;
    }

    @PostMapping("/SignUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest rb) {
        // 请求体参数
        String name = rb.getName();
        String password = rb.getPassword();
        String email = rb.getEmail();

        int len =  getSpecificUserInfo.getUserList(name).size();
        if (len> 0){
            return new ResponseEntity<>("users info exist", HttpStatus.CONFLICT);
        } else {
            int res = 0;
            try {
                res = insertUser.insertUser(name, password, email);
                res +=initbanlance.initBanlance(name,0);
                res+= initvip.initVipStatus(name,0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (res == 3)
                return new  ResponseEntity<>("sign up success", HttpStatus.OK);
        }
        return new ResponseEntity<>("sign up fail",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
