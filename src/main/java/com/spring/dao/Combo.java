package com.spring.dao;

import com.spring.domain.SqlTable.ComboTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface Combo {
    @Select("select * from combo")
    public List<ComboTable>getAllInfo();

    @Update("update combo set price_now=#{now} ,price_origin=#{origin} where name=#{name}")
    public void upgradeItem(String name,int now,int origin);
}
