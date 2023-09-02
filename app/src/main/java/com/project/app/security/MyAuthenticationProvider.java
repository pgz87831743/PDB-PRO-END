package com.project.app.security;

import com.project.app.basic.entity.SysUser;
import com.project.app.basic.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private SysUserService sysUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SysUser authUser = sysUserService.lambdaQuery()
                .eq(StringUtils.hasText(authentication.getName()), SysUser::getUsername, authentication.getName())
                .eq(StringUtils.hasText(authentication.getCredentials().toString()), SysUser::getPassword, authentication.getCredentials().toString())
                .one();
        if (authUser == null) {
            throw new BadCredentialsException("用户名或密码错误！");
        }
        MyAuthentication myAuthentication = new MyAuthentication();
        myAuthentication.setAuthentication(true);
        myAuthentication.setPrincipal(authUser);
        myAuthentication.setName(authentication.getName());
        return myAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
