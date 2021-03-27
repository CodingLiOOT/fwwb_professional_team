package com.fwwb.back_end.utils.webclientUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: back_end
 * @description: test
 * @author: CodingLiOOT
 * @create: 2021-03-07 14:30
 * @version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTestData implements Serializable {
    public Integer line;
    public List<Integer> station;
}
