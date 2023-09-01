package com.project.app.work.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@TableName("sys_shop")
public class SysShop {

    /**
     * 主键
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品说明
     */
    private String description;

    /**
     * 图片
     */
    private String img;

    /**
     * 价格
     */
    private Double price;

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
