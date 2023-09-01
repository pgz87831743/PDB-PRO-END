package com.project.app.work.service.impl;

import com.project.app.work.entity.SysShop;
import com.project.app.work.mapper.SysShopMapper;
import com.project.app.work.service.SysShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import java.util.List;
/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysShopServiceImpl extends ServiceImpl<SysShopMapper, SysShop> implements SysShopService {
        @Override
        public Page<SysShop> page(PageDTO pageDTO) {
        return lambdaQuery()
        .like(StringUtils.hasText(pageDTO.getSearch()),SysShop::getName,pageDTO.getSearch())
        .or()
        .like(StringUtils.hasText(pageDTO.getSearch()),SysShop::getDescription,pageDTO.getSearch())
        .orderByDesc(SysShop::getCreateTime).page(pageDTO.getMybatisPage());
        }
        @Override
        public List<SysShop> listAll() {
            return list();
        }
        @Override
        public SysShop getSysShopById(String id) {
            return getById(id);
        }
        @Override
        public boolean deleteSysShopById(String id) {
            return removeById(id);
        }
        @Override
        public boolean addSysShop(SysShop obj) {
            return save(obj);
        }
        @Override
        public boolean updateSysShopById(SysShop obj) {
            return updateById(obj);
        }
}
