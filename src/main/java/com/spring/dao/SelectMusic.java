package com.spring.dao;

import com.spring.domain.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SelectMusic {
    @Select("select * from musicList where Id = #{id}")
    public Music getById(@Param("id") int id);
}

