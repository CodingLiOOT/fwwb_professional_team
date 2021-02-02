package com.fwwb.back_end.dao;

import com.fwwb.back_end.entity.TripBean;
import com.fwwb.back_end.entity.UsersBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {

    @Select("select users.userID,users.areaID from users,trips where trips.in_time>'2020-05-21 00:00:00' and trips.in_time<'2020-08-15 00:00:00' and users.userID=trips.userID")
    List<UsersBean> selectRangeByTime();

    @Select("select users.userID,users.areaID from users,trips where trips.in_time between '2020-05-21 00:00:00' and '2020-08-15 00:00:00' and users.userID=trips.userID")
    List<UsersBean> selectRangeByBetween();

    @Select("select * from users")
    List<UsersBean> selectAllUsersByStar();

    @Select("select in_station,out_station from trips")
    List<TripBean> selectAllStationsByInOut();

    @Select("select * from trips")
    List<TripBean> selectAllTrip();

    @Select("select users.userID,users.areaID from trips join users on trips.userID = users.userID where in_time between '2020-05-21 00:00:00' and '2020-08-15 00:00:00'")
    List<UsersBean> selectByJoin();


}
