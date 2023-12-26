package com.spring.controller;

import com.spring.dao.MusicList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UpMusicFileCtr {
    @Value("${musicfileDir}")
    private String musicfiledir;


    private MusicList musicList;

    @Autowired
    public void setMusicList(MusicList musicList) {
        this.musicList = musicList;
    }

    @PostMapping("/uploadMusicFile")
    public String method(@RequestParam("mp3") MultipartFile file) throws Exception {
        int cnt = musicList.getSongList().size();
        String Name = file.getOriginalFilename();
        System.out.println(Name);
        String fileName = Integer.toString(cnt + 1) + ".mp3";
        file.transferTo(new File(musicfiledir + fileName));
        return fileName;
    }
}

