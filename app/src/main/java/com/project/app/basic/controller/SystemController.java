package com.project.app.basic.controller;

import com.project.app.basic.service.SystemService;
import com.project.app.model.dto.RegisterDTO;
import com.project.app.util.R;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;


    @PostMapping("register")
    @Operation(summary = "注册用户")
    public R addSysUser(@RequestBody RegisterDTO obj) {
        return R.success(systemService.register(obj), "注册成功");
    }


}
