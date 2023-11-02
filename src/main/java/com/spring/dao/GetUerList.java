package com.spring.dao;

import com.spring.domain.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GetUerList {
    @Select("select * from users")
    public List<UserInfo> getUserList();
}
