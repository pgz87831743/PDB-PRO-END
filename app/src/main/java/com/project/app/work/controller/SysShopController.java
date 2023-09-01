package com.project.app.work.controller;

import com.project.app.util.R;
import com.project.app.work.entity.SysShop;
import com.project.app.work.service.SysShopService;
import com.project.app.model.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sysShop")
@RequiredArgsConstructor
    public class SysShopController {

    private final SysShopService iSysShopService;


    @PostMapping("page")
    @Operation(summary = "商品表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iSysShopService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "商品表列表")
    @PreAuthorize("hasRole('USER')")
    public R listAll() {
        return R.success(iSysShopService.listAll());
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "商品表详情")
    public R getSysShopById(@PathVariable("id") String id) {
        return R.success(iSysShopService.getSysShopById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "商品表删除")
    public R deleteSysShopById(@PathVariable("id") String id) {
        return R.success(iSysShopService.deleteSysShopById(id),"删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "商品表新增")
    public R addSysShop(@RequestBody SysShop obj) {
        return R.success(iSysShopService.addSysShop(obj),"新增成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "商品表更新")
    public R updateSysShopById(@RequestBody SysShop obj) {
        return R.success(iSysShopService.updateSysShopById(obj),"更新成功");
    }

}
