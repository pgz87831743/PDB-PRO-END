package com.project.app.security;

import com.alibaba.fastjson.JSON;
import com.project.app.config.MyConfiguration;
import com.project.app.util.JwtUtils;
import com.project.app.util.R;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private MyConfiguration myConfiguration;

    @Resource
    private JwtUtils jwtUtils;

    private static AntPathMatcher antPathMatcher=new AntPathMatcher();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean isIgnoreUrl=false;
        for (String ignoreUrl : myConfiguration.getIgnoreUrl()) {
            if (antPathMatcher.match(ignoreUrl,request.getRequestURI())) {
                isIgnoreUrl=true;
            }
        }

        if (isIgnoreUrl){
            filterChain.doFilter(request,response);
        }else{
            String token=request.getHeader("Authorization");
            if (StringUtils.hasText(token)) {
                if (!jwtUtils.checkToken(token, response)) {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(R.toLogin("令牌不合法！")));
                }else{
                    filterChain.doFilter(request, response);
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }

    }
}
