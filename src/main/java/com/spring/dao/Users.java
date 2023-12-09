package com.spring.dao;

import com.spring.domain.SqlTable.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Users {
    @Select("select * from users where name = #{name}")
    public List<UserInfo> getUserInfo(@Param("name") String name);
    @Select("select * from users")
    public List<UserInfo> getUserList();

    @Insert("insert into users (name,password,email) values (#{name},#{password},#{email})")
    public int insertUser(String name,String password,String email);


    @Update("update users set active = #{status}  where name = #{name} ")
    public int upgradeUserActiveStatus(int status,String name);
}
