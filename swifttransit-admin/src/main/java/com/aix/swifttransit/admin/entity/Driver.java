package com.aix.swifttransit.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 司机表
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("driver")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 司机ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 司机姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 擅长车辆类型，多个用逗号分隔
     */
    private String preferredTruckTypes;

    /**
     * 所属机构ID
     */
    private Long organizationId;

    /**
     * 图片信息
     */
    private String picture;

    /**
     * 工作日期
     */
    private LocalDate workDate;

    /**
     * 工作状态 0休息 1上班
     */
    private Integer workStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
