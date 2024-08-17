package com.aix.swifttransit.common.core.vo;

import lombok.Data;

/**
 * 鉴权之后给客户端的 token
 */
@Data
public class JwtToken {

    private String token;
}
