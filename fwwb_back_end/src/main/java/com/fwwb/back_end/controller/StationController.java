package com.fwwb.back_end.controller;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;
import com.fwwb.back_end.service.StationService;
import com.fwwb.back_end.utils.exceptionHandler.exception.DefinitionException;
import com.fwwb.back_end.utils.exceptionHandler.exception.ErrorEnum;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Range;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        List<StrokeBean> strokes;
        ArrayListMultimap<String, StrokeBean> people_map = ArrayListMultimap.create();
        ArrayListMultimap<Integer, StrokeBean> age_map = ArrayListMultimap.create();
        String[] formatter={
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
                "yyyy年第ww周",
                "yyyy-MM"
        };

        strokes = stationService.getStrokeRangeHour(info);
        strokes.forEach(strokeBean -> {
            people_map.put(new DateTime(strokeBean.getTime()).toString(formatter[info.getGranularity()-1]), strokeBean);
            age_map.put(strokeBean.getAgeRange(), strokeBean);
        });

        HashMap<String, Object> data = new HashMap<>();

        List<HashMap<String, Object>> stationData = new ArrayList<>();
        people_map.keySet().forEach((key) -> {
            HashMap<String, Object> time = new HashMap<>();
            time.put("entranceNum", people_map.get(key).size());
            time.put("time", key);
            stationData.add(time);
        });

        HashMap<String, Integer> age = new HashMap<>();
        age.put("underage", age_map.get(1).size());
        age.put("teen", age_map.get(2).size());
        age.put("middle", age_map.get(3).size());
        age.put("old", age_map.get(4).size());

        data.put("stationData", stationData);
        data.put("age", age);
        return data;
    }
}
