package com.project.app.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.project.app.basic.entity.SysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;


@Configuration
@MapperScan({"com.project.app.basic.mapper","com.project.app.work.mapper"})
public class MybatisPlusConfig implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        try {
            SysUser principal = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.strictInsertFill(metaObject, "createBy", String.class,principal.getUsername());
        } catch (Exception e) {
            this.strictInsertFill(metaObject, "createBy", String.class, "admin");
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
