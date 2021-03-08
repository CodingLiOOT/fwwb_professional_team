package com.fwwb.back_end.utils.webclientUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: back_end
 * @description: test
 * @author: CodingLiOOT
 * @create: 2021-03-07 14:27
 * @version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTest implements Serializable {
    private Integer code;
    private String msg;
    private List<ResultTestData> data;
}
