package com.spring.domain.RequestionParams;

import lombok.Data;

@Data
public class AddMusicRequest {
    String musicName;
    String  singerName_album;
    String timeLength;
    String url;
}
