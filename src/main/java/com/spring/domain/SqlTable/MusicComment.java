package com.spring.domain.SqlTable;

import lombok.Data;

import java.sql.Date;

@Data
public class MusicComment {
    int Id;
    String musicName;
    String userName;
    String commentContent;
    Date date;
}
