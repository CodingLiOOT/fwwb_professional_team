package com.fwwb.back_end.dao;

import com.fwwb.back_end.entity.UsersBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper {

    @Select("select userID from users")
    List<UsersBean> selectAll();
}
