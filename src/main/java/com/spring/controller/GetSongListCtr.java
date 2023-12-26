package com.spring.controller;

import com.spring.dao.MusicList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class GetSongListCtr {
    private MusicList musicList;

    @Autowired
    public void setMusicList(MusicList musicList) {
        this.musicList = musicList;
    }

    @GetMapping("/getSongList")
    public ResponseEntity<?> fn() {
        System.out.println(1);
        return new ResponseEntity<>(musicList.getSongList(), HttpStatusCode.valueOf(200));
    }
}
