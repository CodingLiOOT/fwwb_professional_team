package com.fwwb.back_end.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: back_end
 * @description: TripBean
 * @author: CodingLiOOT
 * @create: 2021-02-01 16:01
 * @version: 1.0
 **/
@ApiModel(value = "行程")
@Data
public class TripBean {
    @ApiModelProperty(name = "userID", value = "用户ID", required = false)
    private String userID;
    @ApiModelProperty(name = "in_station", value = "入站站点ID", required = false)
    private int in_station;
    @ApiModelProperty(name = "in_time", value = "入站时间", required = false)
    private Timestamp in_time;
    @ApiModelProperty(name = "out_station", value = "出站站点ID", required = false)
    private int out_station;
    @ApiModelProperty(name = "out_time", value = "出站时间", required = false)
    private Timestamp out_time;
    @ApiModelProperty(name = "channelID", value = "渠道ID", required = false)
    private int channelID;
    @ApiModelProperty(name = "price", value = "价格", required = false)
    private int price;

}
