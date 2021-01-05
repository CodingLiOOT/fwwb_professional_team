package com.fwwb.back_end.intercepter;

import com.alibaba.fastjson.JSON;

import com.fwwb.back_end.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: Token拦截类
 * @author: CodingLiOOT
 * @create: 2020-12-18 15:15
 * @version: 1.0
 **/
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        System.out.println(token);
        if(token != null){
            boolean result = JWTUtils.verify(token);
            if(result){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            Map<String,String>res=new HashMap<>();
            res.put("msg","token 验证失败");
            res.put("code","402");
            response.setStatus(402);
            response.getWriter().append(JSON.toJSONString(res));
            //System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
