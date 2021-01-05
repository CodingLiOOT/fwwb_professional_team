package com.fwwb.back_end.service.impl;

import com.fwwb.back_end.dao.UserMapper;
import com.fwwb.back_end.entity.UserBean;
import com.fwwb.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: back_end
 * @description: user service implementation
 * @author: CodingLiOOT
 * @create: 2021-01-05 13:04
 * @version: 1.0
 **/
@Service("accountService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    /**
     * @Title: login
     * @Description: 登录接口
     * @param userName: 用户名
     * @return: java.util.List<com.fwwb.back_end.entity.UserBean>
     * @throw: null
     * @Author: CodeingLiOOT
     * @Date: 2021/1/5 13:24
     */
    public List<UserBean> login(String userName){
        return userMapper.loginByUsername(userName);
    }

    @Override
    public void register(UserBean bean){
        userMapper.register(bean);
    }

    @Override
    public List<UserBean> selectAll(){
        return userMapper.selectALL();}
}
