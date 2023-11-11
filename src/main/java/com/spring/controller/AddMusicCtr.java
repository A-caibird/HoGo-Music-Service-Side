package com.spring.controller;

import com.spring.dao.InsertMusic;
import com.spring.domain.AddMusicRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "*")
public class AddMusicCtr {
    private InsertMusic insertMusic;

    @Autowired
    public void setInsertMusic(InsertMusic insertMusic) {
        this.insertMusic = insertMusic;
    }
    @PostMapping("/addMusic")
    public ResponseEntity<String> addMusic(@RequestBody AddMusicRequest rq) {
        int res = 0;
        try {
            res = insertMusic.insertMusic(rq.getMusicName(), rq.getSingerName_album(), rq.getTimeLength(), rq.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (res == 1)
            return new ResponseEntity<String>("sucess", HttpStatus.OK);
        return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

