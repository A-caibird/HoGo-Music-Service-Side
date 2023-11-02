package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import com.spring.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface GetSpecificUserInfo {
    @Select("select * from users where name = #{name}")
    public List<UserInfo> getUserList(@Param("name") String name);
}
