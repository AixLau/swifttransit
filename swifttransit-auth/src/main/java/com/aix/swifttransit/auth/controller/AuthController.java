package com.aix.swifttransit.auth.controller;

import com.aix.swifttransit.auth.dto.LoginResponse;
import com.aix.swifttransit.auth.dto.LoginUsernameRequest;
import com.aix.swifttransit.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "登录鉴权", description = "处理用户的登录和令牌管理")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "用户登录", description = "用户提供用户名和密码进行登录，返回访问令牌")
    @PostMapping("/login")
    public LoginResponse loginUsernamePassword(@RequestBody @Valid LoginUsernameRequest loginUsernameRequest) {
        return this.authService.loginUserNamePassword(loginUsernameRequest);
    }

    @Operation(summary = "刷新令牌", description = "使用刷新令牌获取新的访问令牌")
    @Parameter(name = "refreshToken", description = "长期刷新令牌", required = true)
    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody @Valid String refreshToken) {
        return authService.refreshToken(refreshToken);
    }


    @Operation(summary = "令牌失效", description = "通过用户名使用户的所有相关令牌失效")
    @DeleteMapping("/invalidate-token")
    public void invalidateToken(@RequestParam String username) {
        authService.invalidateToken(username);
    }
}
