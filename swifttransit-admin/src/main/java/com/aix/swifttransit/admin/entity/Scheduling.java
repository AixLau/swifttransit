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
 * 排班管理表
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("scheduling")
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排班ID
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 员工ID，关联员工表
     */
    private Long employeeId;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 工作模式：1-礼拜制, 2-连续制
     */
    private Integer workMode;

    /**
     * 具体工作模式描述，如早班、晚班等
     */
    private String workPattern;

    /**
     * 排班日期
     */
    private LocalDate scheduleDate;

    /**
     * 是否是绑定司机, 1-是, 0-否
     */
    private Integer isDriver;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
