package com.fwwb.back_end.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
            "date_format(in_time,'%Y-%m-%d %H:00:00') in_hour," +
            "count(userID) " +
            "from trips " +
            "where in_time between '2020-02-02 12:00:00' " +
            "and '2020-02-02 16:00:00' " +
            "group by in_hour " +
            "order by in_time;")
    List<HashMap<String,Object>> getPassenger();
}
