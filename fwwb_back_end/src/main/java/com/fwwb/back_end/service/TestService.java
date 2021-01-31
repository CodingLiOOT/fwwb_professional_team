package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.UsersBean;

import java.util.List;

public interface TestService {
    public List<UsersBean> selectAll();

    public List<UsersBean> selectRangeByTime();
}
