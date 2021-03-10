package com.fwwb.back_end.controller;

import com.fwwb.back_end.utils.exceptionHandler.exception.DefinitionException;
import com.fwwb.back_end.utils.exceptionHandler.exception.ErrorEnum;
import com.fwwb.back_end.entity.AccountBean;
import com.fwwb.back_end.service.AccountService;
import com.fwwb.back_end.utils.resultUtils.ResponseResultBody;
import com.fwwb.back_end.utils.resultUtils.Result;
import com.fwwb.back_end.utils.token.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserLogInController {

    @Autowired
    private AccountService accountService;

    /**
     * @param user:
     * @Title: doLogin
     * @Description: 登录响应Controller
     * @return: org.springframework.http.ResponseEntity
     * @throw:
     * @Author: CodeingLiOOT
     * @Date: 2021/1/16 17:57
     */
    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/login")
    public Map<String, Object> doLogin(@RequestBody AccountBean user) {
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        List<AccountBean> accountBeans = accountService.login(user.getUserName());
        if (accountBeans.size() == 0 || !accountBeans.get(0).getPassword().equals(user.getPassword())) {
            throw new DefinitionException(ErrorEnum.ERROR_NICKNAME_OR_PASSWORD);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("user", accountBeans.get(0));
        String token = JWTUtils.sign(accountBeans.get(0));
        data.put("token", token);
        return data;
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/register")
    public Result doRegister(@RequestBody AccountBean user) {
        List<AccountBean> accountBean = accountService.login(user.getUserName());
        if (accountBean.size() != 0) {
            throw new DefinitionException(ErrorEnum.DUPLICATE_USERNAME);
        }
        accountService.register(user);
        return Result.success("注册成功");
    }
}
