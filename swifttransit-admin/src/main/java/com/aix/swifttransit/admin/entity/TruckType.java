package com.aix.swifttransit.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 车辆类型表
 * </p>
 *
 * @author aix
 * @since 2024-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("truck_type")
public class TruckType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车型ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车辆类型名称
     */
    private String name;

    /**
     * 长，单位：米
     */
    private BigDecimal measureLong;

    /**
     * 宽，单位：米
     */
    private BigDecimal measureWidth;

    /**
     * 高，单位：米
     */
    private BigDecimal measureHigh;

    /**
     * 准载重量，单位：吨
     */
    private BigDecimal allowableLoad;

    /**
     * 准载体积，单位：立方米
     */
    private BigDecimal allowableVolume;

    /**
     * 车型数量
     */
    private Integer num;

    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;


}
