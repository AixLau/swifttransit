package com.aix.swifttransit.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 所属机构ID，关联机构表
     */
    private Long organizationId;

    /**
     * 系统角色
     */
    private String systemRole;

    /**
     * 账号状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
