package com.project.app.basic.service.impl;

import com.project.app.basic.entity.SysUser;
import com.project.app.basic.service.SysUserService;
import com.project.app.basic.service.SystemService;
import com.project.app.enums.RoleEnum;
import com.project.app.execptions.MyRuntimeException;
import com.project.app.model.dto.RegisterDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private SysUserService sysUserService;


    @Override
    public boolean register(RegisterDTO registerDTO) {
        if (
                !StringUtils.hasText(registerDTO.getAvatar())||
                !StringUtils.hasText(registerDTO.getUsername())||
                !StringUtils.hasText(registerDTO.getPassword())||
                !StringUtils.hasText(registerDTO.getNickname())

        ){
            throw new MyRuntimeException("请填写完整注册信息！");
        }
        SysUser newUser = new SysUser();
        newUser.setPassword(registerDTO.getPassword());
        newUser.setAvatar(registerDTO.getAvatar());
        newUser.setNickname(registerDTO.getNickname());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setRole(RoleEnum.USER.name());
        return sysUserService.save(newUser);
    }
}
