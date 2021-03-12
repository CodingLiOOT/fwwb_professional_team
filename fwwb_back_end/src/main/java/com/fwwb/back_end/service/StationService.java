package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 0e625c1be989031543aa45e35969247434761153
import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description:
 * @date: Created in 12:01 2021/2/4
 */
public interface StationService {
    public List<HashMap<String,Object>> getPassengerInfoByHour(StationInfo info);

    public List<StrokeBean> getEntranceStrokeByTime(StationInfo info);

    public List<StrokeBean> getOutStrokeByTime(StationInfo info);
<<<<<<< HEAD
=======

    public List<HashMap<String, Object>> getLineStationInfo();

    public ArrayList<Integer> getLine();
>>>>>>> 0e625c1be989031543aa45e35969247434761153
}
