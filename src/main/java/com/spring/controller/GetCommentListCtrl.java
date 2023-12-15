package com.spring.controller;

import com.spring.dao.CommentList;
import com.spring.domain.SqlTable.MusicComment;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class GetCommentListCtrl {
    CommentList commentList;
    @Autowired
    public  void setCommentList(CommentList commentList){
        this.commentList = commentList;
    }
    @GetMapping("getSongComment")
    public ResponseEntity<?> getSongComment(){
        List<MusicComment>  list = null;
        try{
            list=commentList.getCommentList();
            }catch (Exception e){
            e.printStackTrace();
            log.error("服务器获取歌曲评价列表错误");
            return new ResponseEntity<>("Internal server error", HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
    }
}
