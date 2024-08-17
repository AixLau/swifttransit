package com.swifttransit.auth.util;

import com.swifttransit.auth.constant.AuthorityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JwtUtil {

    /**
     * 密钥实例
     */
    public static final SecretKey KEY = Keys.hmacShaKeyFor(AuthorityConstant.PrivateKey.getBytes());

    /**
     * 生成 accessToken 有效期 1 个小时
     */
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + AuthorityConstant.ACCESS_TOKEN_EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    /**
     * 生成 refreshToken 有效期 7天
     */
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + AuthorityConstant.REFRESH_TOKEN_EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    /**
     * 检查 token 是否过期
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateToken(token);
        return expiration.before(new Date());
    }

    /**
     * 从 token 中获取过期时间
     */
    private Date getExpirationDateToken(String token) {
        return getClaimToken(token, Claims::getExpiration);
    }

    /**
     * 从 token 中获取用户名
     */
    public String getUsername(String token) {
        return getClaimToken(token, Claims::getSubject);
    }

    /**
     * 从 token 中获取指定的 Claims
     */
    private <T> T getClaimToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = getAllClaimToken(token);
        return claimsTFunction.apply(claims);
    }

    /**
     * 从 token 中获取所有的 Claims
     */
    private Claims getAllClaimToken(String token) {
        return Jwts.parser().verifyWith(KEY)
                .build()
                .parseSignedClaims(token).getPayload();
    }
}
