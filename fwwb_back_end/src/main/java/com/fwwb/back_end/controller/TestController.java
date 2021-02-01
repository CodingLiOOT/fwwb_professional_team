package com.fwwb.back_end.controller;


import com.fwwb.back_end.entity.UsersBean;
import com.fwwb.back_end.service.TestService;
import com.fwwb.back_end.utils.JedisInstance;
import com.fwwb.back_end.utils.Result;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @program: back_end
 * @description: UsersController
 * @author: CodingLiOOT
 * @create: 2021-01-30 17:28
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    @Autowired
    private TestService usersService;

    @GetMapping("/getAll")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity getAll()
    {
        List<UsersBean> users=usersService.selectAll();
        @Cleanup Jedis jedis= JedisInstance.getInstance().getResource();
        jedis.set("abc","abc");
        return ResponseEntity.ok(Result.success(users.get(0)));
    }
}
