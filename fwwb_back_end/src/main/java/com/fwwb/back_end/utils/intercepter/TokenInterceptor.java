package com.fwwb.back_end.utils.intercepter;

import com.fwwb.back_end.utils.exceptionHandler.exception.DefinitionException;
import com.fwwb.back_end.utils.exceptionHandler.exception.ErrorEnum;
import com.fwwb.back_end.utils.token.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: demo
 * @description: Token拦截类
 * @author: CodingLiOOT
 * @create: 2020-12-18 15:15
 * @version: 1.0
 **/
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static ThreadLocal<String> threadLocal=new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        threadLocal.set(token);
        //System.out.println(token);
//        if(token != null){
//            boolean result = JWTUtils.verify(token);
//            if(!result){
//                throw new DefinitionException(ErrorEnum.SIGNATURE_NOT_MATCH);
//            }
//        }
        log.info("user {} accepted",JWTUtils.verifyAndGetUserName(token,"username"));
        return true;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        try{
//            Map<String,String>res=new HashMap<>();
//            res.put("msg","token 验证失败");
//            res.put("code","402");
//            response.setStatus(402);
//            response.getWriter().append(JSON.toJSONString(res));
//            //System.out.println("认证失败，未通过拦截器");
//        }catch (Exception e){
//            e.printStackTrace();
//            response.sendError(500);
//            return false;
//        }
//        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();
    }

    public static String getToken(){
        return threadLocal.get();
    }
}
