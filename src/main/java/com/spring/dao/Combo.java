package com.spring.dao;

import com.spring.domain.SqlTable.ComboTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Combo {
    @Select("select * from combo")
    public List<ComboTable>getComboInfo();
}
