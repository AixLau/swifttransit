package com.aix.swifttransit.auth.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "用户状态枚举")
public enum UserStatusEnum {

    @Schema(description = "正常")
    ENABLE(1),

    @Schema(description = "禁用")
    DISABLED(0);

    private final int code;

    UserStatusEnum(int code) {
        this.code = code;
    }

    public static UserStatusEnum fromCode(int code) {
        for (UserStatusEnum status : UserStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的用户状态代码: " + code);
    }
}

