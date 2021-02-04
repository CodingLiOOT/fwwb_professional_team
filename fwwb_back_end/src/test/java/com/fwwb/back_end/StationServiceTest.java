package com.fwwb.back_end;

import com.fwwb.back_end.service.StationService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description:
 * @date: Created in 12:12 2021/2/4
 */

public class StationServiceTest extends BackEndApplicationTests{

    @Autowired
    StationService stationService;

    @Test
    public void test(){
        List<HashMap<String,Object>> list=stationService.getPassenger();
        System.out.println(list.get(0));
        Assert.assertEquals("登录失败",20L,stationService.getPassenger().get(3).get("count(userID)"));
    }
}
