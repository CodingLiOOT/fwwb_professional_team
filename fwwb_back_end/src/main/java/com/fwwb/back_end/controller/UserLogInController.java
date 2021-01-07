package com.fwwb.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.fwwb.back_end.entity.Result;
import com.fwwb.back_end.entity.UserBean;
import com.fwwb.back_end.service.UserService;
import com.fwwb.back_end.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserLogInController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/test")
    public String hello() {
        return "Hello,Spring";
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity doLogin(@RequestBody UserBean user) {
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        //System.out.println(user.getUserName()+user.getPassword());
        List<UserBean> userBeans = userService.login(user.getUserName());
        if (userBeans.size() == 0 || !userBeans.get(0).getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Result.fail("用户名或密码错误"));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("user", userBeans.get(0));
        String token = JWTUtils.sign(userBeans.get(0));
        data.put("token", token);
        return ResponseEntity.ok(Result.success(data, "登陆成功"));
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/register")
    public ResponseEntity doRegister(@RequestBody UserBean user) {
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        //System.out.println(user.getUserName()+user.getPassword());
        List<UserBean> userBean = userService.login(user.getUserName());
        if (userBean.size() != 0) {
            return ResponseEntity.status(401).body(Result.fail("该用户名已被注册"));
        }
        userService.register(user);
        return ResponseEntity.ok(Result.success("注册成功"));
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/getAllUser")
    public ResponseEntity doTest() {
        List<UserBean> userBeans = userService.selectAll();
        if (userBeans.size() == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("msg", "没有用户");
            map.put("data", userBeans);
            return ResponseEntity.status(401).body(Result.fail("没有用户"));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("data", userBeans);
        return ResponseEntity.ok(Result.success(userBeans, "查询成功"));
    }
}
