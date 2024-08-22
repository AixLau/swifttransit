package com.aix.swifttransit.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 运费模板表
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("carriage")
public class Carriage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运费模板id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 模板类型，1-同城寄 2-省内寄 3-经济区互寄 4-跨省
     */
    private Integer templateType;

    /**
     * 运送类型，1-普快 2-特快
     */
    private Integer transportType;

    /**
     * 关联城市，1-全国 2-京津冀 3-江浙沪 4-川渝 5-黑吉辽
     */
    private String associatedCity;

    /**
     * 首重价格
     */
    private Double firstWeight;

    /**
     * 续重价格
     */
    private Double continuousWeight;

    /**
     * 轻抛系数
     */
    private Integer lightThrowingCoefficient;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;


}
