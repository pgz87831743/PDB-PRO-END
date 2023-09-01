package com.project.app.basic.service;

import com.project.app.basic.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysUserService extends IService<SysUser>, UserDetailsService {
        Page<SysUser> page(PageDTO pageDTO);
        List<SysUser> listAll();
        SysUser getSysUserById(String id);
        boolean deleteSysUserById(String id);
        boolean addSysUser(SysUser obj);
        boolean updateSysUserById(SysUser obj);
}
