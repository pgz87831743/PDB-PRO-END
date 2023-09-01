package com.project.app.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.app.basic.entity.SysUser;
import com.project.app.basic.mapper.SysUserMapper;
import com.project.app.basic.service.SysUserService;
import com.project.app.model.dto.PageDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public Page<SysUser> page(PageDTO pageDTO) {
        return lambdaQuery().orderByDesc(SysUser::getCreateTime).page(pageDTO.getMybatisPage());
    }

    @Override
    public List<SysUser> listAll() {
        return list();
    }

    @Override
    public SysUser getSysUserById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteSysUserById(String id) {
        return removeById(id);
    }

    @Override
    public boolean addSysUser(SysUser obj) {
        return save(obj);
    }

    @Override
    public boolean updateSysUserById(SysUser obj) {
        return updateById(obj);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = lambdaQuery()
                .eq(SysUser::getUsername, username)
                .one();
        if (user==null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }else{
            return user;
        }

    }
}
