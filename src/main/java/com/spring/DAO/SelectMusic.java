package com.spring.DAO;

import com.spring.Entity.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SelectMusic {
    @Select("select * from musicList where Id = #{id}")
    public Music getById(@Param("id") int id);
}
