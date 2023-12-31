package com.spring.dao;

import com.spring.domain.SqlTable.Music;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MusicList {
    @Select("select * from musicList")
    public List<Music> getSongList();

    @Insert("insert into musicList (musicName,singerName_album ,timeLength,url) values (#{musicName},#{singerName_album},#{timeLength},#{url})")
    public int insertMusic(String musicName, String singerName_album, String timeLength, String url);

    @Update("update musicList set musicName = #{newName}, singerName_album = #{singer},timeLength = #{time} where musicName = #{oldName}")
    public int upgrade(String newName, String oldName, String singer, String time);
}
