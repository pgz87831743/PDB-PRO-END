package com.project.app.work.service;

import com.project.app.work.entity.SysShop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysShopService extends IService<SysShop> {
        Page<SysShop> page(PageDTO pageDTO);
        List<SysShop> listAll();
        SysShop getSysShopById(String id);
        boolean deleteSysShopById(String id);
        boolean addSysShop(SysShop obj);
        boolean updateSysShopById(SysShop obj);
}
