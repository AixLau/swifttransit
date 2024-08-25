package com.aix.swifttransit.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆信息表
 *
 * @TableName truck
 */
@TableName(value = "truck")
@Data
public class Truck implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 车辆ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 车牌号码
     */
    private String licensePlate;
    /**
     * 准载重量
     */
    private BigDecimal allowableLoad;
    /**
     * 准载体积
     */
    private BigDecimal allowableVolume;
    /**
     * 品牌
     */
    private String brand;
    /**
     * GPS设备ID
     */
    private String deviceGpsId;
    /**
     * 关联司机
     */
    private String driverIds;
    /**
     * 司机数量
     */
    private Integer driverNum;
    /**
     * 满载系数
     */
    private BigDecimal loadingRatio;
    /**
     * 图片信息
     */
    private String picture;
    /**
     * 运输状态 0：已到达 1：运输中
     */
    private Integer runStatus;
    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;
    /**
     * 关联线路
     */
    private String transportLineName;
    /**
     * 车辆行驶证信息ID
     */
    private Long truckLicenseId;
    /**
     * 车辆类型ID
     */
    private Long truckTypeId;
    /**
     * 车辆类型名称
     */
    private String truckType;
    /**
     * 工作状态 0：停用 1：启用
     */
    private Integer workStatus;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}