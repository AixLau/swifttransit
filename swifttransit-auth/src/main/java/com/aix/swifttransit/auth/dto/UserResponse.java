package com.aix.swifttransit.auth.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Feign Client 的返回对象，用于接收用户服务返回的用户信息。
 */
@Data
public class UserResponse implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID，主键")
    @TableId(value = "id")
    private Long id;

    @Schema(description = "用户名，唯一")
    private String username;

    @Schema(description = "密码，存储加密后的密码")
    private String password;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "账户状态，1表示正常，0表示禁用")
    private Integer status;

    @Schema(description = "逻辑删除 1删除  0未删除")
    private Integer deleted;

    @Schema(description = "账户创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Data createTime;

    @Schema(description = "账户更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Data updateTime;

    @Schema(description = "最后登录时间")
    @TableField(fill = FieldFill.INSERT)
    private Data lastLogin;
}
