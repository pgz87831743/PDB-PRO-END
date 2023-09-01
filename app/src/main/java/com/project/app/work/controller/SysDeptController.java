package com.project.app.work.controller;

import com.project.app.util.R;
import com.project.app.work.entity.SysDept;
import com.project.app.work.service.SysDeptService;
import com.project.app.model.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sysDept")
@RequiredArgsConstructor
    public class SysDeptController {

    private final SysDeptService iSysDeptService;


    @PostMapping("page")
    @Operation(summary = "部门表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iSysDeptService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "部门表列表")
    public R listAll() {
        return R.success(iSysDeptService.listAll());
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "部门表详情")
    public R getSysDeptById(@PathVariable("id") String id) {
        return R.success(iSysDeptService.getSysDeptById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "部门表删除")
    public R deleteSysDeptById(@PathVariable("id") String id) {
        return R.success(iSysDeptService.deleteSysDeptById(id),"删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "部门表新增")
    public R addSysDept(@RequestBody SysDept obj) {
        return R.success(iSysDeptService.addSysDept(obj),"新增成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "部门表更新")
    public R updateSysDeptById(@RequestBody SysDept obj) {
        return R.success(iSysDeptService.updateSysDeptById(obj),"更新成功");
    }

}
