package com.fwwb.back_end.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel(value = "用户")
@Data
public class AccountBean implements Serializable {
    @ApiModelProperty(name = "ID", value = "用户ID", required = false)
    private long ID;
    @ApiModelProperty(name = "userName", value = "用户名", required = true)
    private String userName;
    @ApiModelProperty(name = "password", value = "用户密码", required = false)
    private String password;
    @ApiModelProperty(name = "submission_date", value = "用户注册日期", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp submission_date;
    @ApiModelProperty(name = "email", value = "用户邮箱", required = false)
    private String email;

    @Override
    public String toString() {
        return "UserBean{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", submission_date=" + submission_date +
                ", email='" + email + '\'' +
                '}';
    }
}
