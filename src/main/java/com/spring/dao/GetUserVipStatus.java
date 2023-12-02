package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GetUserVipStatus {
    @Select("select vipStatus from vip where username = #{name}")
    public int get(String name);
}
