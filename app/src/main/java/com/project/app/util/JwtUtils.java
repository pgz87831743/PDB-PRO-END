package com.project.app.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.app.config.MyConfiguration;
import com.project.app.security.MyAuthentication;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtils {

    @Resource
    private MyConfiguration myConfiguration;

    public String genToken(MyAuthentication authentication) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + (myConfiguration.getExpiresTime().getSeconds() * 1000)))
                .withAudience(JSON.toJSONString(authentication))
                .sign(Algorithm.HMAC256(myConfiguration.getSecKey()));
    }


    public boolean checkToken(String token, HttpServletResponse response) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(myConfiguration.getSecKey())).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        String jwtAuthentication = JWT.decode(token).getAudience().get(0);
        Date expiresAt = JWT.decode(token).getExpiresAt();
        LocalDateTime expiresAtTime = expiresAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        MyAuthentication authentication = JSON.parseObject(jwtAuthentication, MyAuthentication.class);
        long expiresTime = Duration.between(LocalDateTime.now(), expiresAtTime).toMinutes();
        System.out.println("还剩"+expiresTime+"分钟过期！");
        if (expiresTime < 5) {
            response.addHeader("access_token", genToken(authentication));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return true;
    }
}
