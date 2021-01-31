package com.fwwb.back_end.dao;

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

    @Select("select * from users")
    List<UsersBean> selectAll();
}
