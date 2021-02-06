package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description:
 * @date: Created in 12:01 2021/2/4
 */
public interface StationService {
    public List<HashMap<String,Object>> getPassengerInfoByHour(StationInfo info);

    public List<StrokeBean> getStrokeRangeHour(StationInfo info);

    public List<StrokeBean> getStrokeRangeWeek(StationInfo info);

    public List<StrokeBean> getStrokeRangeDay(StationInfo info);

    public List<StrokeBean> getStrokeRangeMonth(StationInfo info);
}
