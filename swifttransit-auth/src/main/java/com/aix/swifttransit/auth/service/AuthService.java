package com.aix.swifttransit.auth.service;

import com.aix.swifttransit.auth.dto.LoginResponse;
import com.aix.swifttransit.auth.dto.LoginUsernameRequest;

public interface AuthService {

    /**
     * 用户提供用户名和密码进行登录
     *
     * @param loginUsernameRequest 账号密码登录请求对象
     * @return 用户登录响应对象
     */
    LoginResponse loginUserNamePassword(LoginUsernameRequest loginUsernameRequest);

    /**
     * 使用刷新令牌获取新的访问令牌。
     *
     * @param refreshToken 用于获取新访问令牌的刷新令牌。
     * @return 包含新访问令牌和刷新令牌的 {@link LoginResponse} 对象。
     */
    LoginResponse refreshToken(String refreshToken);

    /**
     * 使指定用户的所有相关令牌失效。
     *
     * @param username 要使令牌失效的用户的用户名。
     */
    void invalidateToken(String username);
}
