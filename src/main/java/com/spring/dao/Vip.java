package com.spring.dao;

import com.spring.domain.SqlTable.VipTable;
import org.apache.ibatis.annotations.*;

import java.sql.Date;

@Mapper
public interface Vip {
    @Update("UPDATE vip SET vipStatus = #{vipStatus},startDate=#{startDate},endDate=#{endDate} Where username = #{username}")
    public int updateVipStatus(@Param("username") String username, @Param("vipStatus") int vipStatus, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Select("select vipStatus from vip where username = #{name}")
    public int getVipStatus(String name);

    @Insert("insert into vip (username,vipStatus) values (#{username},#{vipStatus})")
    public int initVipStatus(String username, int vipStatus);

    @Select("select * from vip where username = #{name}")
    public VipTable getVipAllInfo(String name);
}
