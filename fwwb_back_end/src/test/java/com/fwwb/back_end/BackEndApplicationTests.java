package com.fwwb.back_end;

import com.fwwb.back_end.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
class BackEndApplicationTests {

    @Before
    public void init(){
        System.out.println("开始测试---------------");
    }

    @After
    public void after(){
        System.out.println("测试结束---------------");
    }
}
