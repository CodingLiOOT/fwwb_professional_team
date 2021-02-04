package com.fwwb.back_end;

import com.fwwb.back_end.service.StationService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Assert.assertSame("登录失败",20,stationService.getPassenger().get(3).get("2020-02-02 15:00:00"));
    }
}
