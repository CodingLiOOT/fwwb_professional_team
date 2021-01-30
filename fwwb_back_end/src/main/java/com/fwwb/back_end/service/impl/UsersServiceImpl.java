package com.fwwb.back_end.service.impl;

import com.fwwb.back_end.dao.UserMapper;
import com.fwwb.back_end.dao.UsersMapper;
import com.fwwb.back_end.entity.UsersBean;
import com.fwwb.back_end.service.UsersService;
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
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<UsersBean> selectAll() {
        return usersMapper.selectAll();
    }
}
