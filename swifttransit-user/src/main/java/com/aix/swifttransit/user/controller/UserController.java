package com.aix.swifttransit.user.controller;


import com.aix.swifttransit.common.mvc.annotation.IgnoreResponseAdvice;
import com.aix.swifttransit.user.dto.UserCredentialsDTO;
import com.aix.swifttransit.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-18
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关的操作接口")
public class UserController {

    private final UserService userService;

    @IgnoreResponseAdvice
    @Operation(summary = "用户名查找用户信息", description = "根据提供的用户名查找对应的用户信息。")
    @GetMapping("/internal/find-by-username")
    public UserCredentialsDTO findByUsername(@RequestParam("username") String username) {
        return this.userService.getByUsername(username);
    }

}