package com.spring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin(origins = "*")
public class UpMusicFileCtr {
    @PostMapping("/uploadMusicFile")
    public String method(@RequestParam("mp3") MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        file.transferTo(new File("/Users/acaibird/Documents/git-project/cloud-music/public/mp3/"+fileName));
        return fileName;
    }
}

