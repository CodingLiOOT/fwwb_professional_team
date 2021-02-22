package com.fwwb.back_end.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: back_end
 * @description: users data bean
 * @author: CodingLiOOT
 * @create: 2021-01-30 17:20
 * @version: 1.0
 **/
@ApiModel(value = "行程用户")
@Data
public class UsersBean {
    @ApiModelProperty(name = "userID",value = "用户ID",required = true)
    String userID;
    @ApiModelProperty(name = "areaID",value = "用户行政区域ID",required = true)
    int areaID;
    @ApiModelProperty(name = "birthYear",value = "用户出生年份",required = true)
    int birthYear;
    @ApiModelProperty(name = "gender",value = "用户性别",required = true)
    int gender;
}
