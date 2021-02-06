package com.fwwb.back_end.controller;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;
import com.fwwb.back_end.service.StationService;
import com.fwwb.back_end.utils.exceptionHandler.exception.DefinitionException;
import com.fwwb.back_end.utils.exceptionHandler.exception.ErrorEnum;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
    public List<HashMap<String, Object>> test(){
        return stationService.getPassengerInfoByHour(null);
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getPassengerByTime")
    public HashMap<String, Object> getPassengerInfoByTime(@RequestBody StationInfo info){
        switch (info.getGranularity()){
            case 1:
                List<StrokeBean> strokes=stationService.getStroke(info);
                ArrayListMultimap<String,StrokeBean> map1=ArrayListMultimap.create();
                ArrayListMultimap<Integer,StrokeBean> map2=ArrayListMultimap.create();
                strokes.forEach(strokeBean -> {
                    map1.put(strokeBean.getTime().toString().split("\\.")[0],strokeBean);
                    map2.put(strokeBean.getAgeRange(),strokeBean);
                });
                HashMap<String,Object> data=new HashMap<>();
                List<HashMap<String,Object>> stationData=new ArrayList<>();
                map1.keySet().forEach((key)->{
                    HashMap<String,Object> time=new HashMap<>();
                    time.put("entranceNum",map1.get(key).size());
                    time.put("time",key);
                    stationData.add(time);
                });
                HashMap<String,Integer> age=new HashMap<>();
                age.put("underage",map2.get(1).size());
                age.put("teen",map2.get(2).size());
                age.put("middle",map2.get(3).size());
                age.put("old",map2.get(4).size());
                data.put("stationData",stationData);
                data.put("age",age);
                return data;
            case 2:

            case 3:

            case 4:

            default:
                throw new DefinitionException(ErrorEnum.BODY_NOT_MATCH);
        }
    }
}
