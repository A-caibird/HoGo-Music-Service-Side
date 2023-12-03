package com.spring.dao;

import com.spring.domain.SqlTable.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Mapper
public interface GetSongList {
    @Select("select * from musicList")
    public List<Music> getSongList();
}
