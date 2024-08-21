package com.aix.swifttransit.common.core.util;

import com.aix.swifttransit.common.core.constant.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.PublicKey;
import java.util.Date;
import java.util.function.Function;

public class JwtUtil {
    /**
     * 密钥实例
     */
    private static final PublicKey PUBLIC_KEY = KeyUtil.loadPublicKey(CommonConstants.PublicKey);


    /**
     * 验证 Access Token
     */
    public static boolean validateAccessToken(String token) {
        Claims claims = getAllClaimToken(token);
        String tokenType = claims.get("type", String.class);
        return "ACCESS".equals(tokenType) && !isTokenExpired(token);
    }

    /**
     * 验证 Refresh Token
     */
    public static boolean validateRefreshToken(String token) {
        Claims claims = getAllClaimToken(token);
        String tokenType = claims.get("type", String.class);
        return "REFRESH".equals(tokenType) && !isTokenExpired(token);
    }

    /**
     * /**
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
