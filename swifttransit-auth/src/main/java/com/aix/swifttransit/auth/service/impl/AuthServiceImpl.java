package com.aix.swifttransit.auth.service.impl;

import com.aix.swifttransit.auth.client.UserClient;
import com.aix.swifttransit.auth.constant.AuthorityConstant;
import com.aix.swifttransit.auth.dto.LoginResponse;
import com.aix.swifttransit.auth.dto.LoginUsernameRequest;
import com.aix.swifttransit.auth.enums.DeletedStatusEnum;
import com.aix.swifttransit.auth.enums.UserStatusEnum;
import com.aix.swifttransit.auth.service.AuthService;
import com.aix.swifttransit.auth.util.JwtTokenUtil;
import com.aix.swifttransit.common.core.constant.CommonConstants;
import com.aix.swifttransit.common.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;
    private final UserClient userClient;

    @Override
    public LoginResponse loginUserNamePassword(LoginUsernameRequest loginUsernameRequest) {
        // 通过 Feign Client 调用用户服务查询用户信息
        var userCredentialsDTO = userClient.findByUsername(loginUsernameRequest.getUsername());

        // 判断用户是否存在
        if (ObjectUtils.isEmpty(userCredentialsDTO) ||
                userCredentialsDTO.getDeleted() == DeletedStatusEnum.DELETED.getCode()) {
            throw new RuntimeException("用户不存在");
        }

        // 校验用户状态
        if (userCredentialsDTO.getStatus() == UserStatusEnum.DISABLED.getCode())
            throw new RuntimeException("用户已被禁用");

        // 校验密码
        if (!passwordEncoder.matches(loginUsernameRequest.getPassword(), userCredentialsDTO.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 将 Refresh Token 存储到 Redis 中，并设置过期时间
        var refreshToken = JwtTokenUtil.generateRefreshToken(userCredentialsDTO.getUsername());
        // 对 Refresh Token 进行 MD5 加密
        String refreshTokenHash = DigestUtils.md5DigestAsHex(refreshToken.getBytes(StandardCharsets.UTF_8));
        redisTemplate.opsForValue().set(AuthorityConstant.REFRESH_TOKEN_KEY + userCredentialsDTO.getUsername(), refreshTokenHash, CommonConstants.REFRESH_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);

        return new LoginResponse()
                .setAccessToken(JwtTokenUtil.generateAccessToken(userCredentialsDTO.getUsername())).
                setRefreshToken(refreshToken);
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        // 验证 refreshToken 是否过期，
        if (JwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("refreshToken已过期");
        }

        // 通过 token 获取到用户名
        var username = JwtUtil.getUsername(refreshToken);
        // 对传入的 Refresh Token 进行 MD5 加密
        String refreshTokenHash = DigestUtils.md5DigestAsHex(refreshToken.getBytes(StandardCharsets.UTF_8));
        // 检查 Redis 中的 MD5 加密后的 Refresh Token 是否存在并且有效
        String storedRefreshTokenHash = redisTemplate.opsForValue().get(AuthorityConstant.REFRESH_TOKEN_KEY + username);
        if (!Objects.equals(refreshTokenHash, storedRefreshTokenHash)) {
            throw new RuntimeException("无效的刷新令牌");
        }

        // 生成新的 Access Token 和 Refresh Token
        String newAccessToken = JwtTokenUtil.generateAccessToken(username);
        String newRefreshToken = JwtTokenUtil.generateRefreshToken(username);

        // 对新的 Refresh Token 进行 MD5 加密
        String newRefreshTokenHash = DigestUtils.md5DigestAsHex(newRefreshToken.getBytes(StandardCharsets.UTF_8));
        // 更新 Redis 中的 MD5 加密后的 Refresh Token
        redisTemplate.opsForValue().set(AuthorityConstant.REFRESH_TOKEN_KEY + username, newRefreshTokenHash, CommonConstants.REFRESH_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);

        return new LoginResponse().setAccessToken(newAccessToken).setRefreshToken(newRefreshTokenHash);
    }

    @Override
    public void invalidateToken(String username) {
        // 删除 Redis 中的 Refresh Token，强制其失效
        redisTemplate.delete(AuthorityConstant.REFRESH_TOKEN_KEY + username);
    }
}
