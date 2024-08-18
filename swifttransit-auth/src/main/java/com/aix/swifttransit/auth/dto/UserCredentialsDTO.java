package com.aix.swifttransit.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户认证信息传输对象")
public class UserCredentialsDTO {

    @Schema(description = "用户名", example = "john_doe")
    private String username;

    @Schema(description = "密码", example = "password123")
    private String password;

    @Schema(description = "是否删除")
    private Integer deleted;

    @Schema(description = "用户状态")
    private Integer status;
}

