package com.fwwb.back_end.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: back_end
 * @description: OD bean
 * @author: CodingLiOOT
 * @create: 2021-04-18 16:42
 * @version: 1.0
 **/
@Data
public class ODBean {
    private Timestamp time;
    private Integer num;
    private Integer in_station;
    private Integer out_station;
}
