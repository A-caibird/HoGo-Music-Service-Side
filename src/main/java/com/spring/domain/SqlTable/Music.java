package com.spring.domain.SqlTable;

import lombok.Data;

@Data
public class Music {
    private int id;
    private String musicName;
    private String singerName_album;
    private String timeLength;
    private String url;
}
