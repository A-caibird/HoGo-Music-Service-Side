package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TopInBanlance {
    @Update("update banlance set banlance = #{banlance} where username = #{username}")
    public int topin(String username,int banlance);
}
