package com.aix.swifttransit.common.core.constant;

public interface CommonConstants {


    /**
     * accessToken 过期时间 1个小时
     */
    int ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60;

    /**
     * refreshToken 过期时间7天
     */
    int REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    /**
     * JWT 公钥
     */
    String PublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkifoJi3N4xg6EqPSduI53WyY9IPTDzmq/Eplvy9Co1uwqbTI3xfdyDEwQfDQnYGYfASoebrdKqzjJAcGRUadFr2QcHrPVqRzkyRQ/tEx9rlTFQrXtgM3Kyj45ZsKRNMXG8Ix6cBqZxTBT4XPA4L6cWTdAwqB824IvpkCmP4ePR9EH9dAt84i0qd+lPD6u9wtXphyBwXUpOzXrYroZjz8W/Qs42Rf1uprbcI3DwLoE1K4iKLp9rFzJt7g1JzMvs1pcfUAFHfPCzz2oLpu5V9Pv9Vj+Ef8qLn23iXx3Vorc8f/6lcCaT4lxNxTyiqQ/SJsFS9u/Cph5ojaZAqmVcpPlQIDAQAB";

    /**
     * JWT 中存储的用户信息的 key
     */
    String JWT_USER_INFO_KEY = "swifttransit-user";

    /**
     * 授权中心的id
     */
    String AUTHORITY_CENTER_SERVICE_ID = "swifttransit-authority";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;

    /**
     * 失败标记
     */
    Integer FAIL = 1;
}
