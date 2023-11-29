package com.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InitVipStatus {
    @Insert("insert into vip (username,vipStatus) values (#{username},#{vipStatus})")
    public int initVipStatus(String username, int vipStatus);
}
