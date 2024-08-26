package com.aix.swifttransit.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户寄递记录表
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_shipments")
public class UserShipments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 寄递记录唯一标识符
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 用户唯一标识符
     */
    private Long userId;

    /**
     * 发件地址ID
     */
    private Long senderAddressId;

    /**
     * 收件地址ID
     */
    private Long receiverAddressId;

    /**
     * 寄递物品名称
     */
    private String itemName;

    /**
     * 寄递物品类型
     */
    private String itemType;

    /**
     * 寄递日期
     */
    private LocalDateTime shipmentDate;

    /**
     * 预估重量（千克）
     */
    private BigDecimal estimatedWeight;

    /**
     * 预估总体积（立方米）
     */
    private BigDecimal estimatedVolume;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;


}
