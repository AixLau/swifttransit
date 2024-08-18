package com.aix.swifttransit.auth.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "用户登录响应对象")
public class LoginResponse {

    @Schema(description = "短期访问令牌")
    private String accessToken;

    @Schema(description = "长期刷新令牌")
    private String refreshToken;
}

