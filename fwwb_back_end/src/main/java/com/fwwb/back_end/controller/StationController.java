package com.fwwb.back_end.controller;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.service.StationService;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<HashMap<String, Object>> getPassengerInfoByTime(@RequestBody StationInfo info){
        switch (info.getGranularity()){
            case 1:
                return stationService.getPassengerInfoByHour(info);
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }

        return stationService.getPassengerInfoByHour(info);

    }
}
