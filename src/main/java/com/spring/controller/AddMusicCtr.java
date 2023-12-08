package com.spring.controller;

import com.spring.dao.MusicList;
import com.spring.domain.RequestionParams.AddMusicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@Transactional(rollbackFor = Exception.class)
public class AddMusicCtr {
    private MusicList musicList;

    @Autowired
    public void setMusicList(MusicList musicList) {
        this.musicList = musicList;
    }

    @PostMapping("/addMusic")
    public ResponseEntity<String> addMusic(@RequestBody AddMusicRequest rq) {
        int res = 0;
        try {
            res = musicList.insertMusic(rq.getMusicName(), rq.getSingerName_album(), rq.getTimeLength(), rq.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (res == 1)
            return new ResponseEntity<>("sucess", HttpStatus.OK);
        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

