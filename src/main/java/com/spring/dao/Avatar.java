package com.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Avatar {
    @Insert("INSERT INTO avatar (name, avatarUrl) VALUES (#{name}, #{url}) " +
        "ON DUPLICATE KEY UPDATE avatarUrl = VALUES(avatarUrl)")
    public int insert(String name, String url);

    @Select("select avatarUrl from avatar where  name = #{name}")
    public String getUrl(String name);
}
