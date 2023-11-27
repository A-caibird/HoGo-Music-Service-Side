package com.spring.dao;

import com.spring.domain.SqlTable.Banlance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QueryBanlance {
    @Select("SELECT  *  from banlance where username = #{name}")
    public List<Banlance> query(@Param("name") String name);
}
