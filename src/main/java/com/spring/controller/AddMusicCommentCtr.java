package com.spring.controller;

import com.spring.dao.CommentList;
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
@Slf4j
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class AddMusicCommentCtr {
    private CommentList commentList;

    @Autowired
    public void setCommentList(CommentList commentList) {
        this.commentList = commentList;
    }

    @PostMapping("/commentSong")
    public ResponseEntity<?> commentSong(@RequestBody Map<String, Object> params) {
        String musicName = (String) params.get("musicName"), userName = (String) params.get("userName"), commentContent = (String) params.get("comment");
        int rows = 0;
        try {
            rows = commentList.addComment(musicName, userName, commentContent);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("增加歌曲评论失败");
            return new ResponseEntity<>("Internal server error", HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
