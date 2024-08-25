package com.aix.swifttransit.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Schema(description = "地址数据传输对象")
public class AddressAddOrUpdateDTO {

    private Long id;

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户的唯一标识符", example = "123")
    private Long userId;

    @Min(value = 0, message = "发货状态只能为 0 或 1")
    @Max(value = 1, message = "发货状态只能为 0 或 1")
    @Schema(description = "默认发货地址：0->否；1->是", example = "0")
    private Integer sendStatus;

    @Min(value = 0, message = "收货状态只能为 0 或 1")
    @Max(value = 1, message = "收货状态只能为 0 或 1")
    @Schema(description = "是否默认收货地址：0->否；1->是", example = "1")
    private Integer receiveStatus;

    @NotBlank(message = "收件人姓名不能为空")
    @Size(max = 64, message = "姓名长度不能超过64个字符")
    @Schema(description = "收件人全名", example = "张三")
    private String name;

    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^[1-9]\\d{10}$", message = "电话号码格式不正确")
    @Schema(description = "收件人联系电话", example = "13800138000")
    private String phone;

    @NotBlank(message = "省份不能为空")
    @Schema(description = "省份名称", example = "湖北省")
    private String province;

    @NotBlank(message = "城市不能为空")
    @Schema(description = "城市名称", example = "武汉市")
    private String city;

    @NotBlank(message = "区域不能为空")
    @Schema(description = "区/县名称", example = "武昌区")
    private String region;

    @NotBlank(message = "详细地址不能为空")
    @Schema(description = "详细街道地址", example = "街道口")
    private String detailAddress;
}
