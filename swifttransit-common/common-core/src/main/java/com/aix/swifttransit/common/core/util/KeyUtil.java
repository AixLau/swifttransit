package com.aix.swifttransit.common.core.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtil {

    /**
     * 从 Base64 编码的字符串中加载 RSA 私钥
     *
     * @param base64PrivateKey Base64 编码的私钥字符串
     * @return PrivateKey 私钥对象
     */
    public static PrivateKey loadPrivateKey(String base64PrivateKey) {
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(base64PrivateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Failed to load private key", e);
        }
    }

    /**
     * 从 Base64 编码的字符串中加载 RSA 公钥
     *
     * @param base64PublicKey Base64 编码的公钥字符串
     * @return PublicKey 公钥对象
     */
    public static PublicKey loadPublicKey(String base64PublicKey) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Failed to load public key", e);
        }
    }
}
