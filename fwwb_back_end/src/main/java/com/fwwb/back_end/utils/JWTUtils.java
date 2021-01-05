package com.fwwb.back_end.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fwwb.back_end.entity.UserBean;


import java.util.Date;

/**
 * @program: demo
 * @description: JWT工具类
 * @author: CodeingLiOOT
 * @create: 2020-12-18 14:56
 * @version: 1.0
 **/
public class JWTUtils {

    private static final long EXPIRE_TIME=10*60*60*1000;
    private static final String TOKEN_SECRET="CodeingLiOOT";

    public static String sign(UserBean user){
        String token=null;
        try{
            Date expiresAt=new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token= JWT.create()
                    .withIssuer("CodeingLiOOT")
                    .withClaim("username",user.getUserName())
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public static boolean verify(String token){
        try{
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                                    .withIssuer("CodeingLiOOT")
                                    .build();
            DecodedJWT jwt=verifier.verify(token);

            System.out.println("认证通过： ");
            System.out.println("username: "+ jwt.getClaim("username").asString());
            System.out.println("过期时间: "+jwt.getExpiresAt());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
