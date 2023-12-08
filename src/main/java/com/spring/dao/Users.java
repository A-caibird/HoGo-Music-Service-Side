package com.spring.dao;

import com.spring.domain.SqlTable.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Users {
    @Select("select * from users where name = #{name}")
    public List<UserInfo> getUserInfo(@Param("name") String name);
    @Select("select * from users")
    public List<UserInfo> getUserList();

    @Insert("insert into users (name,password,email) values (#{name},#{password},#{email})")
    public int insertUser(String name,String password,String email);
}
