package com.project.app.basic.service;

import com.project.app.basic.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.enums.FileTypeEnum;
import com.project.app.model.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SysFileService extends IService<SysFile> {

        List<SysFile> upload(MultipartFile[] files, FileTypeEnum fileTypeEnum);


        void download(String fileId);

        boolean delete(String fileId);
}
