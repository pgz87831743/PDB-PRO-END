package com.project.app.work.service.impl;

import com.project.app.work.entity.SysDept;
import com.project.app.work.mapper.SysDeptMapper;
import com.project.app.work.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import java.util.List;
/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
        @Override
        public Page<SysDept> page(PageDTO pageDTO) {
        return lambdaQuery()
        .like(StringUtils.hasText(pageDTO.getSearch()),SysDept::getName,pageDTO.getSearch())
        .or()
        .like(StringUtils.hasText(pageDTO.getSearch()),SysDept::getAddress,pageDTO.getSearch())
        .orderByDesc(SysDept::getCreateTime).page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysDept> listAll() {
            return list();
        }
        @Override
        public SysDept getSysDeptById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysDeptById(String id) {
            return removeById(id);
        }
        @Override
        public boolean addSysDept(SysDept obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysDeptById(SysDept obj) {
            return updateById(obj);
        }
}
