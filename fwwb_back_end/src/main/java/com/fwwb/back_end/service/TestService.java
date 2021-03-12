package com.fwwb.back_end.service;

import com.fwwb.back_end.utils.webclientUtils.ResultTest;
import com.fwwb.back_end.entity.TripBean;
import com.fwwb.back_end.entity.UsersBean;

import java.util.List;
import java.util.concurrent.Future;

public interface TestService {

    List<UsersBean> selectRangeByTime();

    List<UsersBean> selectRangeByBetween();

    List<UsersBean> selectAllUsersByStar();

    List<TripBean> selectAllStationsByInOut();

    List<TripBean> selectAllTrip();

    List<UsersBean> selectByJoin();

    Future<String> testAsync();

    Future<ResultTest> testAsyncData();
}
