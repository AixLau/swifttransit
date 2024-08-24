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
 * 快递员信息表
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("courier")
public class Courier implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 快递员id
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 快递员账号
     */
    private String account;

    /**
     * 快递员姓名
     */
    private String name;

    /**
     * 快递员手机号
     */
    private String phone;

    /**
     * 所属机构ID
     */
    private Long organizationId;

    /**
     * 快递员年龄
     */
    private Integer age;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;


}
