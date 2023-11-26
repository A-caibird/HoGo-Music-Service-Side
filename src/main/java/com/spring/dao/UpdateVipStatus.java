package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;

@Mapper
public interface UpdateVipStatus {
    @Update("UPDATE vip SET vipStatus = #{vipStatus},startDate=#{startDate},endDate=#{endDate} Where username = #{username}")
    public int updateVipStatus(@Param("username") String username, @Param("vipStatus") int vipStatus, @Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
