package com.fwwb.back_end;

import com.fwwb.back_end.BackEndApplicationTests;
import com.fwwb.back_end.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: back_end
 * @description: account service test
 * @author: CodingLiOOT
 * @create: 2021-02-03 19:49
 * @version: 1.0
 **/
public class AccountServiceTest extends BackEndApplicationTests {

    @Autowired
    AccountService accountService;

    @Test
    public void testSample(){
        Assert.assertSame("登录失败",1,accountService.login("Ljz123hhh").size());
    }
}
