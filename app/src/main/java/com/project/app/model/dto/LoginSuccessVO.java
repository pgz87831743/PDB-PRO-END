package com.project.app.model.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Data
public class LoginSuccessVO {
    private Authentication authentication;
    private String token;
}
