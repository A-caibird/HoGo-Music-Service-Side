package com.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsertMusic {
    @Insert("insert into musicList (musicName,singerName_album ,timeLength,url) values (#{musicName},#{singerName_album},#{timeLength},#{url})")
    public int insertMusic(String musicName,String singerName_album,String timeLength,String url);
}
