package com.aix.swifttransit.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "账号密码登录请求对象")
public class LoginUsernamePasswordRequest {
    @Schema(description = "用户名", example = "user", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 15, message = "用户名长度应在3到15个字符之间")
    private String username;

    @Schema(description = "用户密码", example = "aix")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 100, message = "密码长度应在6到100个字符之间")
    private String password;

}
