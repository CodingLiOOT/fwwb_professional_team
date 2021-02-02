package com.fwwb.back_end.service.impl;

import com.fwwb.back_end.dao.TestMapper;
import com.fwwb.back_end.entity.TripBean;
import com.fwwb.back_end.entity.UsersBean;
import com.fwwb.back_end.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: back_end
 * @description: UsersServiceImpl
 * @author: CodingLiOOT
 * @create: 2021-01-30 17:26
 * @version: 1.0
 **/
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<UsersBean> selectRangeByTime() {
        return testMapper.selectRangeByTime();
    }

    @Override
    public List<UsersBean> selectRangeByBetween() {
        return testMapper.selectRangeByBetween();
    }

    @Override
    public List<UsersBean> selectAllUsersByStar() {
        return testMapper.selectAllUsersByStar();
    }

    @Override
    public List<TripBean> selectAllStationsByInOut() {
        return testMapper.selectAllStationsByInOut();
    }

    @Override
    public List<TripBean> selectAllTrip() {
        return testMapper.selectAllTrip();
    }

    @Override
    public List<UsersBean> selectByJoin() {
        return testMapper.selectByJoin();
    }
}
