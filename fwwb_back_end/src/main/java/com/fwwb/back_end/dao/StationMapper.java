package com.fwwb.back_end.dao;

import com.fwwb.back_end.entity.StationInfo;
import com.fwwb.back_end.entity.StrokeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author： Harry Zhou
 * @description: Station Mapper
 * @date: Created in 11:30 2021/2/4
 */

@Mapper
@Repository
public interface StationMapper {
    @Select("select " +
            "date_format(in_time,'%Y-%m-%d %H:00:00') time," +
            "count(userID) entranceNum " +
            "from trips " +
            "where in_station = #{stationName} "+
            "and in_time between #{startTime} " +
            "and #{endTime} " +
            "group by time " +
            "order by in_time;")
    List<HashMap<String,Object>> getPassengerInfoByHour(StationInfo info);

    @Select("select " +
            //"date_format(in_time,'%Y-%m-%d %H:00:00') as time," +
            "out_time as time, "+
            "Year(Now()) - users.birthYear as age, " +
            "1 as tripType, "+
            "stations.lineID as lineID, "+
            "trips.out_station as stationName, "+
            "users.gender as gender "+
            "from trips,users,stations " +
            "where trips.userID = users.userID and stations.stationName=trips.out_station "+
            "and trips.out_station = #{stationName} "+
            "and trips.out_time between #{startTime} " +
            "and #{endTime} " +
            "order by out_time;")
    List<StrokeBean> getOutStrokeByTime(StationInfo info);

    @Select("select " +
            //"date_format(in_time,'%Y-%m-%d %H:00:00') as time," +
            "in_time as time, "+
            "Year(Now()) - users.birthYear as age, " +
            "2 as tripType, "+
            "stations.lineID as lineID, "+
            "trips.in_station as stationName, "+
            "users.gender as gender "+
            "from trips,users,stations " +
            "where trips.userID = users.userID and stations.stationName=trips.in_station "+
            "and trips.in_station = #{stationName} "+
            "and trips.in_time between #{startTime} " +
            "and #{endTime} " +
            "order by in_time;")
    List<StrokeBean> getEntranceStrokeByTime(StationInfo info);

    @Select("select " +
            "lineID as line, " +
            "stationName as station " +
            "from stations " +
            "order by line,station asc;"
    )
    List<HashMap<String, Object>> getLineStationInfo();

    @Select("select distinct " +
            "lineID as line " +
            "from stations " +
            "order by line asc;")
    ArrayList<Integer> getLine();
}

