package com.aix.swifttransit.auth.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "逻辑删除状态枚举")
public enum DeletedStatusEnum {

    @Schema(description = "未删除")
    NOT_DELETED(0),

    @Schema(description = "已删除")
    DELETED(1);

    private final int code;

    DeletedStatusEnum(int code) {
        this.code = code;
    }

    public static DeletedStatusEnum fromCode(int code) {
        for (DeletedStatusEnum status : DeletedStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的逻辑删除状态代码: " + code);
    }
}

