package com.project.app.work.service;

import com.project.app.work.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysDeptService extends IService<SysDept> {
        Page<SysDept> page(PageDTO pageDTO);
        List<SysDept> listAll();
        SysDept getSysDeptById(String id);
        boolean deleteSysDeptById(String id);
        boolean addSysDept(SysDept obj);
        boolean updateSysDeptById(SysDept obj);
}
