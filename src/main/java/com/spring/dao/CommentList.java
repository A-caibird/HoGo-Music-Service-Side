package com.spring.dao;

import com.spring.domain.SqlTable.MusicComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentList {
    @Select("select * from commentList")
    public List<MusicComment> getCommentList();

}
