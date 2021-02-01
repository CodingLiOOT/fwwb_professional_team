package com.fwwb.back_end.service.impl;

import com.fwwb.back_end.dao.TestMapper;
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
    private TestMapper usersMapper;

    @Override
    public List<UsersBean> selectAll() {
        return usersMapper.selectAll();
    }

    @Override
    public List<UsersBean> selectRangeByTime() {
        return usersMapper.selectRangeByTime();
    }
}
