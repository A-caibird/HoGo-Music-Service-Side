package com.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsertUser {
    @Insert("insert into users (name,password,email) values (#{name},#{password},#{email})")
    public int insertUser(String name,String password,String email);
}
