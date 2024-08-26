package com.aix.swifttransit.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 禁寄物品表
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("restricted_items")
public class RestrictedItems implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 禁寄物品唯一标识符
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 禁寄物品名称
     */
    private String name;

    /**
     * 禁寄原因
     */
    private String reason;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
