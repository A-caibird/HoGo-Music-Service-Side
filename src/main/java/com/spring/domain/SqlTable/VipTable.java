package com.spring.domain.SqlTable;

import lombok.Data;

import java.sql.Date;

@Data
public class VipTable {
    String username;
    int vipStatus;
    Date startDate;
    Date endDate;
}
