package com.aix.swifttransit.common.core.constant;

public interface CommonConstants {

    /**
     * JWT 公钥
     */
    String PublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq9pntPxgHiY7oOcuDfBVKclafCw1wc5Jc8jgmq2DnqLbU5CbK02X9tr3kWyho/yO0EY4NwgGXFSxj2nKNJvUhoZMNFvbqr7ovwxmL7I+ga828pPSHubNTt10ouK805GZwXfIISmJ3U1fPxC/rv3BQa1uX4+oAuvGMZqQ/1znGiuAMJEIGjvU7qEhvZDa/MVjPzqYBBoMK7Wou16jh54KYD80jRW7pvBgnrh94XCPAOQccWSdc9TXbLxYRSkFxDqgjym2PTS2dxU0zjLHKa2Ml+67bBnI5IQvREHp9KZ5NcZBRfnexnkIFLUjZzjOv1TiXN+E32VUM8g/OyF4lGwQlQIDAQAB";

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
