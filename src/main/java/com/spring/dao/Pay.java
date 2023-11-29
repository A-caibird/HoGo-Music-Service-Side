package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Pay {
    @Update("UPDATE banlance SET banlance = #{banlance} where username = #{name}")
    public int pay(@Param("name") String name,@Param("banlance") int  banlance);
}