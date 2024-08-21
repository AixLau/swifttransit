package com.aix.swifttransit.auth.util;

import com.aix.swifttransit.auth.constant.AuthorityConstant;
import com.aix.swifttransit.common.core.constant.CommonConstants;
import com.aix.swifttransit.common.core.util.KeyUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.function.Function;

public class JwtTokenUtil {

    /**
     * 密钥实例
     */
    private static final PrivateKey PRIVATE_KEY = KeyUtil.loadPrivateKey(AuthorityConstant.PrivateKey);
    private static final PublicKey PUBLIC_KEY = KeyUtil.loadPublicKey(CommonConstants.PublicKey);


    /**
     * 生成 accessToken 有效期 1 个小时
     */
    public static String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("type", "ACCESS")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + CommonConstants.ACCESS_TOKEN_EXPIRATION))
                .signWith(PRIVATE_KEY, Jwts.SIG.RS256)
                .compact();
    }

    /**
     * 生成 refreshToken 有效期 7天
     */
    public static String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("type", "REFRESH")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + CommonConstants.REFRESH_TOKEN_EXPIRATION))
                .signWith(PRIVATE_KEY, Jwts.SIG.RS256)
                .compact();
    }

    /**
     * 验证 Access Token
     */
    public boolean validateAccessToken(String token) {
        Claims claims = getAllClaimToken(token);
        String tokenType = claims.get("type", String.class);
        return "ACCESS".equals(tokenType) && !isTokenExpired(token);
    }

    /**
     * 验证 Refresh Token
     */
    public boolean validateRefreshToken(String token) {
        Claims claims = getAllClaimToken(token);
        String tokenType = claims.get("type", String.class);
        return "REFRESH".equals(tokenType) && !isTokenExpired(token);
    }

    /**
     * 检查 token 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateToken(token);
        return !expiration.before(new Date());
    }

    /**
     * 从 token 中获取过期时间
     */
    private static Date getExpirationDateToken(String token) {
        return getClaimToken(token, Claims::getExpiration);
    }

    /**
     * 从 token 中获取用户名
     */
    public static String getUsername(String token) {
        return getClaimToken(token, Claims::getSubject);
    }

    /**
     * 从 token 中获取指定的 Claims
     */
    private static <T> T getClaimToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = getAllClaimToken(token);
        return claimsTFunction.apply(claims);
    }

    /**
     * 从 token 中获取所有的 Claims
     */
    private static Claims getAllClaimToken(String token) {

        return Jwts.parser().verifyWith(PUBLIC_KEY)
                .build()
                .parseSignedClaims(token).getPayload();
    }
}
