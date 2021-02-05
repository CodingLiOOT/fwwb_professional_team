package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.StationInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description:
 * @date: Created in 12:01 2021/2/4
 */
public interface StationService {
    public List<HashMap<String,Object>> getPassengerInfoByHour(StationInfo info);
}
