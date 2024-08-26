package com.aix.swifttransit.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 物品信息表
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("items")
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物品唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 物品类型
     */
    private String type;

    /**
     * 物品描述
     */
    private String description;

    /**
     * 是否为禁寄物品
     */
    private Boolean isRestricted;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;


}
