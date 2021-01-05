package com.fwwb.back_end.dao;

import com.fwwb.back_end.entity.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user")
    List<UserBean> selectALL();

    @Select("select * from user where userName = #{userName}")
    public List<UserBean> loginByUsername(String userName);

    //@SelectKey(statement = "select max(ID) from user", before = true, resultType = Integer.class, keyProperty = "ID")
    @Insert("insert into user (userName, email, password,submission_date) values (#{userName}, #{email}, #{password},NOW())")
    //@Options(useGeneratedKeys = true,keyProperty = "ID",keyColumn = "ID")
    void register(UserBean param);


}
