package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.TripBean;
import com.fwwb.back_end.entity.UsersBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestService {

    List<UsersBean> selectRangeByTime();

    List<UsersBean> selectRangeByBetween();

    List<UsersBean> selectAllUsersByStar();

    List<TripBean> selectAllStationsByInOut();

    List<TripBean> selectAllTrip();

    List<UsersBean> selectByJoin();

}
