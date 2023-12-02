package com.spring.domain.RequestionParams;

import lombok.Data;

@Data
public class TopInRequest {
    String username;
    String payMethod;
    int amount;
}
