package com.project.app.security;

import com.project.app.basic.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
public class MyAuthentication implements Authentication {

    private boolean authentication;
    private String  name;
    private SysUser principal;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return principal.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authentication = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }
}
