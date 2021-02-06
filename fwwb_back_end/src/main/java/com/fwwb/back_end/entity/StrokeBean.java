package com.fwwb.back_end.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @program: back_end
 * @description: 单次行程实体类-统计的基本单位
 * @author: CodingLiOOT
 * @create: 2021-02-05 13:55
 * @version: 1.0
 **/
@ApiModel(value = "行程类-统计基本单位")
@Data
public class StrokeBean implements Serializable {
    @ApiModelProperty(name = "time",value = "进出站时间",required = true)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    @ApiModelProperty(name = "stationName",value = "站名",required = false)
    private int stationName;
    @ApiModelProperty(name = "lineID",value = "线路名",required = false)
    private int lineID;
    @ApiModelProperty(name = "age",value = "年龄",required = false)
    private int age;
    @ApiModelProperty(name = "workdayType",value = "日期类型",required = false)
    private int workdayType;
    @ApiModelProperty(name = "gender",value = "性别",required = false)
    private int gender;
    @ApiModelProperty(name = "tripType",value = "行程类型",required = true)
    private int tripType;

    public int getAgeRange(){
        if(age<18) return 1;
        if(age<45) return 2;
        if(age<69) return 3;
        return 4;
    }
}
