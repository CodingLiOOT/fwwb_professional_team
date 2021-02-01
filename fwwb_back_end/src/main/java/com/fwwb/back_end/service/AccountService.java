package com.fwwb.back_end.service;

import com.fwwb.back_end.entity.AccountBean;

import java.util.List;


public interface AccountService {

    public List<AccountBean> login(String userName);

    public void register(AccountBean bean);

}

