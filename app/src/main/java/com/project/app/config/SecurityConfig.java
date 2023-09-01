package com.project.app.config;

import com.alibaba.fastjson.JSON;
import com.project.app.basic.entity.SysUser;
import com.project.app.util.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        http.formLogin()
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    SysUser principal = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    principal.setPassword(null);
                    response.getWriter().write(JSON.toJSONString(R.success( SecurityContextHolder.getContext().getAuthentication(),"登录成功！")));
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(R.toLogin(exception.getMessage())));
                });

        http.authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**"
                        ,"/doc.html"
                        ,"/webjars/**"
                        ,"/v3/api-docs/**"
                        ,"/attachment/**"
                        ,"/file/upload"
                        ,"/system/register"
                )
                .permitAll()
                .anyRequest()
                .authenticated();


        http.logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(R.success(null,"退出成功")));
                });




//        http.httpBasic(AbstractHttpConfigurer::disable);

        http.exceptionHandling(e -> {
            //未授权
            e.accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(R.toLogin("暂无权限")));
            });


            //未登录
            e.authenticationEntryPoint((request, response, authException) -> {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(R.toLogin("请先登录")));
            });
        });




        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}
