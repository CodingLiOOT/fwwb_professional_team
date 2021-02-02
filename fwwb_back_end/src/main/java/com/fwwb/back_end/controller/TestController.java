package com.fwwb.back_end.controller;


import com.fwwb.back_end.entity.TripBean;
import com.fwwb.back_end.service.TestService;
import com.fwwb.back_end.utils.resultUtils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: back_end
 * @description: UsersController
 * @author: CodingLiOOT
 * @create: 2021-01-30 17:28
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/getAll")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity getAll()
    {
        //List<UsersBean> users=usersService.selectAllUsersByID();
        List<TripBean> trips=testService.selectAllStationsByInOut();
        Map<Integer, Integer> cnt=new HashMap<>();
        trips.forEach(tripBean -> cnt.merge(tripBean.getIn_station(),1,Integer::sum));
        return ResponseEntity.ok(Result.success(cnt));
    }
}
