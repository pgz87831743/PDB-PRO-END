package com.project.app.work.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@TableName("sys_dept")
public class SysDept {

    /**
     * 主键
     */
    private String id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门地址
     */
    private String address;

    /**
     * 部门信息
     */
    private String info;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;


}
