package com.spring.domain;

import lombok.Data;

@Data
public class UserInfo {
    private String name;
    private String password;
    private Integer ID;
    private String email;
    private Integer active;
}
