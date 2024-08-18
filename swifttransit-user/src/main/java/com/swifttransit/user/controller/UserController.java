package com.swifttransit.user.controller;


import com.swifttransit.user.entity.User;
import com.swifttransit.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-18
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 获取全部用户信息
     *
     * @return 用户对象列表
     */
    @Operation(summary = "获取全部用户信息")
    @GetMapping("/all")
    public List<User> getAllUser() {
        return this.userService.list();
    }
}
