package com.fwwb.back_end.controller;

import com.fwwb.back_end.utils.Result;
import com.fwwb.back_end.entity.AccountBean;
import com.fwwb.back_end.service.AccountService;
import com.fwwb.back_end.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserLogInController {

    @Autowired
    private AccountService userService;

    /**
     * @Title: doLogin
     * @Description: 登录响应Controller
     * @param user:
     * @return: org.springframework.http.ResponseEntity
     * @throw:
     * @Author: CodeingLiOOT
     * @Date: 2021/1/16 17:57
     */
    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity doLogin(@RequestBody AccountBean user) {
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        List<AccountBean> accountBeans = userService.login(user.getUserName());
        if (accountBeans.size() == 0 || !accountBeans.get(0).getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Result.fail("用户名或密码错误"));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("user", accountBeans.get(0));
        String token = JWTUtils.sign(accountBeans.get(0));
        data.put("token", token);
        return ResponseEntity.ok(Result.success(data, "登陆成功"));
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/register")
    public ResponseEntity doRegister(@RequestBody AccountBean user) {
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        //System.out.println(user.getUserName()+user.getPassword());
        List<AccountBean> accountBean = userService.login(user.getUserName());
        if (accountBean.size() != 0) {
            return ResponseEntity.status(401).body(Result.fail("该用户名已被注册"));
        }
        userService.register(user);
        return ResponseEntity.ok(Result.success("注册成功"));
    }
}
