package com.project.app.basic.controller;

import com.project.app.basic.entity.SysFile;
import com.project.app.basic.service.SysFileService;
import com.project.app.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Tag(name = "文件管理")
public class SysFileController {

    private final SysFileService sysFileService;


    @PostMapping("upload")
    @Schema(description = "文件上传")


    @Parameters({
            @Parameter(name = "files", description = "文件", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "fileTypeEnum", description = "文件类型", required = true, in = ParameterIn.QUERY)
    })
    public List<SysFile> upload(MultipartFile[] files, FileTypeEnum fileTypeEnum) {
        return sysFileService.upload(files, fileTypeEnum);
    }


    @GetMapping("download/{fileId}")
    @Schema(description = "下载")
    public void download(@Parameter(description = "附件Id") @PathVariable("fileId") String fileId) {
        sysFileService.download(fileId);
    }

    @DeleteMapping("delete/{fileId}")
    @Schema(description = "删除")
    public boolean delete(@Parameter(description = "附件Id") @PathVariable("fileId") String fileId) {
        return sysFileService.delete(fileId);
    }

}
