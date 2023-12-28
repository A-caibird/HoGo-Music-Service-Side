package com.spring.controller.user;

import com.spring.dao.Users;
import com.spring.domain.RequestionParams.StopUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@Transactional(rollbackFor = Exception.class)
public class DeactivateUser {
    private Users users;

    @Autowired
    public void setUsers(Users users) {
        this.users = users;
    }

    @PostMapping("/upgradeUserActiveStatus")
    public ResponseEntity<?> main(@RequestBody StopUserRequest params) {
        String usernamem = params.getName();
        int status= params.getStatus();
        int rows = 0;
        try {
            rows = users.upgradeUserActiveStatus(status,usernamem);
//            System.out.println("更改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatusCode.valueOf(500));
        }
        if (rows == 1) return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
        return new ResponseEntity<>("fail", HttpStatusCode.valueOf(500));
    }
}
