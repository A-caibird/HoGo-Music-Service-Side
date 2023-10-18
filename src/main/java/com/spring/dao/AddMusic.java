package com.spring.dao;

import com.spring.domain.Music;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddMusic {
    @Insert("insert into musicList (musicName,singerName_album,timeLength,url) values (#{musicName},#{singerName_album},#{timeLength},#{url})")
    public void addMusic(Music a);
}
