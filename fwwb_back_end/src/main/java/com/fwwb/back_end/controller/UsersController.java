package com.fwwb.back_end.controller;


import com.fwwb.back_end.entity.UsersBean;
import com.fwwb.back_end.service.UsersService;
import com.fwwb.back_end.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: back_end
 * @description: UsersController
 * @author: CodingLiOOT
 * @create: 2021-01-30 17:28
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity getAll()
    {
        List<UsersBean> users=usersService.selectAll();
        return ResponseEntity.ok(Result.success(users.get(0)));
    }
}
