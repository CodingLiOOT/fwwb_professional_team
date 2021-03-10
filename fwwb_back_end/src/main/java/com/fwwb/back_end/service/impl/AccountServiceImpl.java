package com.fwwb.back_end.service.impl;

import com.fwwb.back_end.dao.AccountMapper;
import com.fwwb.back_end.entity.AccountBean;
import com.fwwb.back_end.service.AccountService;
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
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

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
    public List<AccountBean> login(String userName) {
        return accountMapper.loginByUsername(userName);
    }

    @Override
    /**
     * @Title: register
     * @Description: 注册接口
     * @param userBean: 用户注册信息
     * @return: void
     * @throw: null
     * @Author: CodeingLiOOT
     * @Date: 2021/2/1 0:05
     */
    public void register(AccountBean userBean) {
        accountMapper.register(userBean);
    }

}
