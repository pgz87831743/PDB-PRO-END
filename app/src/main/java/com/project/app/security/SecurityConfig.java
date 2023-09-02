package com.project.app.security;

import com.alibaba.fastjson.JSON;
import com.project.app.basic.entity.SysUser;
import com.project.app.config.MyConfiguration;
import com.project.app.model.dto.LoginSuccessVO;
import com.project.app.util.JwtUtils;
import com.project.app.util.R;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {




    @Resource
    private MyAuthenticationProvider myAuthenticationProvider;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Resource
    private MyConfiguration myConfiguration;


    @Resource
    private JwtUtils jwtUtils;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.formLogin()
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    SysUser principal = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    principal.setPassword(null);

                    LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
                    loginSuccessVO.setAuthentication(authentication);
                    loginSuccessVO.setToken(jwtUtils.genToken((MyAuthentication) authentication));
                    response.getWriter().write(JSON.toJSONString(R.success(loginSuccessVO, "登录成功！")));
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(R.toLogin(exception.getMessage())));
                });

        http.authorizeHttpRequests()
                .requestMatchers(
                        myConfiguration.getIgnoreUrl()
                )
                .permitAll()
                .anyRequest()
                .authenticated();

        http.logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(R.success(null, "退出成功")));
                });


        //设置session 无状态
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authenticationProvider(myAuthenticationProvider);



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


        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }




}
