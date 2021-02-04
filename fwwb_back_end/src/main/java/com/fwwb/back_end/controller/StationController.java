package com.fwwb.back_end.controller;

import com.fwwb.back_end.service.StationService;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return stationService.getPassenger();
    }
}
