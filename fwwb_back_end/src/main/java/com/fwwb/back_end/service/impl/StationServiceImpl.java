package com.fwwb.back_end.service.impl;

import com.fwwb.back_end.dao.StationMapper;
import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;
import com.fwwb.back_end.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description:
 * @date: Created in 12:02 2021/2/4
 */

@Service("stationService")
public class StationServiceImpl implements StationService {
    @Autowired
    private StationMapper stationMapper;

    @Override
    /**
     * @author Harry Zhou
     * @description
     * @date 12:09 2021/2/4
     * @param
     * @return {@link List< HashMap< String, Integer>>}
     * @throws
     **/
    public List<HashMap<String,Object>> getPassengerInfoByHour(StationInfo info){return stationMapper.getPassengerInfoByHour(info);}

    @Override
    public List<StrokeBean> getEntranceStrokeByTime(StationInfo info) {
        return stationMapper.getEntranceStrokeByTime(info);
    }

    @Override
    public List<StrokeBean> getOutStrokeByTime(StationInfo info) {
        return stationMapper.getOutStrokeByTime(info);
    }
}
