package com.project.app.basic.service;

import com.project.app.model.dto.RegisterDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SystemService {

        boolean register(RegisterDTO registerDTO);
}
