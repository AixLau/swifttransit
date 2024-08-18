package com.aix.swifttransit.user.service;

import com.aix.swifttransit.user.dto.UserCredentialsDTO;
import com.aix.swifttransit.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-18
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名查找用户信息
     *
     * @param username 用户名
     * @return UserResponse 用户信息响应对象
     */
    UserCredentialsDTO getByUsername(String username);
}
