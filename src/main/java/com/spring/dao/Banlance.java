package com.spring.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Banlance {
    @Select("SELECT  *  from banlance where username = #{name}")
    public List<com.spring.domain.SqlTable.Banlance> query(@Param("name") String name);

    @Update("update banlance set banlance = #{banlance} where username = #{username}")
    public int topin(String username, int banlance);

    @Insert("insert into banlance (username,banlance) values (#{username},#{banlance})")
    public int initBanlance(String username, int banlance);

}
