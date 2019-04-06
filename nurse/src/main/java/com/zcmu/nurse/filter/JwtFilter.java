package com.zcmu.nurse.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import units.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    public JwtUtil jwtUtil;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求头内容
        String authHeader = request.getHeader("Authorization");
        //请求头内容是否已Bearer开头
        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            //获取token
            String token = authHeader.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {

                }
            }catch (Exception e){
                throw new RuntimeException("时间过期！！");
            }
        }
        return true;
    }
}
