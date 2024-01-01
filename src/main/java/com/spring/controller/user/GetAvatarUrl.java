package com.spring.controller.user;

import com.spring.dao.Avatar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@Slf4j
public class GetAvatarUrl {
    private Avatar avatar;

    @Autowired
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @GetMapping("/getAvatarUrl")
    public ResponseEntity<?> handler(@RequestParam("name") String name) {
        String res = avatar.getUrl(name);
        log.info(res);
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(200));
    }
}
