package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.UserBean;

import java.util.List;


public interface UserService {

    public List<UserBean> login(String userName);

    public void register(UserBean bean);

    public List<UserBean> selectAll();
}

