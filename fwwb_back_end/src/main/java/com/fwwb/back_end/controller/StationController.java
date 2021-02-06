package com.fwwb.back_end.controller;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;
import com.fwwb.back_end.service.StationService;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Range;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author： Harry Zhou
 * @description: Station Controller
 * @date: Created in 11:29 2021/2/4
 */

@RestController
@RequestMapping(value = "/api/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/test")
    @ResponseResultBody
    @CrossOrigin
    public List<HashMap<String, Object>> test() {
        return stationService.getPassengerInfoByHour(null);
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getPassengerByTime")
    public HashMap<String, Object> getPassengerInfoByTime(@RequestBody StationInfo info) {
        checkArgument(Range.closed(1,4).contains(info.getGranularity()),"时间粒度不合法");

        List<StrokeBean> entranceStrokes;
        List<StrokeBean> outStrokes;
        ArrayListMultimap<String, StrokeBean> in_people_map = ArrayListMultimap.create();
        ArrayListMultimap<Integer, StrokeBean> in_age_map = ArrayListMultimap.create();
        ArrayListMultimap<String, StrokeBean> out_people_map = ArrayListMultimap.create();
        ArrayListMultimap<Integer, StrokeBean> out_age_map = ArrayListMultimap.create();
        String[] formatter={
                "yyyy-MM-dd HH:00:00",
                "yyyy-MM-dd",
                "yyyy年第ww周",
                "yyyy-MM"
        };

        entranceStrokes = stationService.getEntranceStrokeByTime(info);
        outStrokes=stationService.getOutStrokeByTime(info);
        entranceStrokes.forEach(strokeBean -> {
            in_people_map.put(new DateTime(strokeBean.getTime()).toString(formatter[info.getGranularity()-1]), strokeBean);
            in_age_map.put(strokeBean.getAgeRange(), strokeBean);
        });
        outStrokes.forEach(strokeBean -> {
            out_people_map.put(new DateTime(strokeBean.getTime()).toString(formatter[info.getGranularity()-1]),strokeBean);
            out_age_map.put(strokeBean.getAgeRange(),strokeBean);
        });

        Set<String> keySet=new HashSet<>();
        keySet.addAll(in_people_map.keySet());
        keySet.addAll(out_people_map.keySet());

        List<HashMap<String, Object>> stationData = new ArrayList<>();
        keySet.forEach((key) -> {
            HashMap<String, Object> time = new HashMap<>();
            time.put("entranceNum", in_people_map.get(key).size());
            time.put("outboundNum",out_people_map.get(key).size());
            time.put("time", key);
            stationData.add(time);
        });

        HashMap<String, Integer> age = new HashMap<>();
        age.put("underage", in_age_map.get(1).size()+out_age_map.get(1).size());
        age.put("teen", in_age_map.get(2).size()+out_age_map.get(2).size());
        age.put("middle", in_age_map.get(3).size()+out_age_map.get(3).size());
        age.put("old", in_age_map.get(4).size()+out_age_map.get(4).size());

        HashMap<String, Object> data = new HashMap<>();
        data.put("stationData", stationData);
        data.put("age", age);
        return data;
    }
}
