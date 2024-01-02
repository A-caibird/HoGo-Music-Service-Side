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
    public int insertUser(String name, String password, String email);


    @Update("update users set active = #{status}  where name = #{name} ")
    public int upgradeUserActiveStatus(int status, String name);

    /**
     * @param oldName  旧的用户名
     * @param username    新用户名
     * @param password 新密码
     * @param email    邮箱
     * @return 受影响的记录数
     */
    @Update("update users set name=#{username},password=#{password},email=#{email} where name=#{oldName}")
    public int upgradeInfo(String oldName, String username, String password, String email);

}
