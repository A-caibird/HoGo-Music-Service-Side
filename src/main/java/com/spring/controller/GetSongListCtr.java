package com.spring.controller;

import com.spring.dao.GetSongList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GetSongListCtr {
    private GetSongList gsl;

    @Autowired
    public void setGsl(GetSongList gsl) {
        this.gsl = gsl;
    }

    @GetMapping("/getSongList")
    public ResponseEntity<?> fn() {
        System.out.println(1);
        return new ResponseEntity<>(gsl.getSongList(), HttpStatusCode.valueOf(200));
    }
}
