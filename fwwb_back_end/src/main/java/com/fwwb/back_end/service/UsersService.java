package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.UsersBean;

import java.util.List;

public interface UsersService {
    public List<UsersBean> selectAll();
}
