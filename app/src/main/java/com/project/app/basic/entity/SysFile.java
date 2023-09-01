package com.project.app.basic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@TableName("sys_file")
@Schema( description = "文件表")
public class SysFile {

    @Schema(description="主键")
    private String id;

    @Schema(description="文件名称")
    private String name;

    @Schema(description="md5")
    private String md5;

    @Schema(description="文件路径")
    private String path;

    @Schema(description="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description="创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;



    @TableField(exist = false)
    private String url;
}
