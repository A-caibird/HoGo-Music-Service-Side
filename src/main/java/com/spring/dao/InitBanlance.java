package com.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InitBanlance {
    @Insert("insert into banlance (username,banlance) values (#{username},#{banlance})")
    public int initBanlance(String username, int banlance);
}
