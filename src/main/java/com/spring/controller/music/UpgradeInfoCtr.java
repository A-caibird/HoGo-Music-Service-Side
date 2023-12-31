package com.spring.controller.music;

import com.spring.dao.MusicList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@Slf4j
public class UpgradeInfoCtr {
    private MusicList musicList;

    @Autowired
    public void setMusicList(MusicList musicList) {
        this.musicList = musicList;
    }

    @PostMapping("modifyMusic")
    public ResponseEntity<?> handler(@RequestBody Map<String, String> params) {
        System.out.println((params));
        try {
            musicList.upgrade(params.get("newName"), params.get("oldName"), params.get("singer"), params.get("time"));
        } catch (Exception e) {
            log.warn(this.getClass().getSimpleName() + " --更新音乐信息时,数据库出现错误");
            return new ResponseEntity<>("no", HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }

}
