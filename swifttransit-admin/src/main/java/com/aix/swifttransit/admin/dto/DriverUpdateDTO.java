package com.aix.swifttransit.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "司机更新数据传输对象")
public class DriverUpdateDTO {


    @Schema(description = "司机ID", example = "1")
    @NotNull(message = "司机ID不能为空")
    private Long id;

    @Schema(description = "司机账号", example = "driver123")
    @NotBlank(message = "司机账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "司机账号只能包含英文和数字字符，最大20个")
    private String account;

    @Schema(description = "司机姓名", example = "张三")
    @NotBlank(message = "司机姓名不能为空")
    @Max(value = 10, message = "司机姓名最多10个字符")
    private String name;

    @Schema(description = "司机手机号", example = "13800138000")
    @NotBlank(message = "司机手机号不能为空")
    @Pattern(regexp = "^\\d{1,11}$", message = "司机手机号只能包含数字，最多11位")
    private String phone;

    @Schema(description = "所属机构ID", example = "2")
    @NotNull(message = "所属机构ID不能为空")
    private Long organizationId;

    @Schema(description = "擅长车辆类型", example = "大型卡车")
    private String preferredTruckTypes;

    @Schema(description = "工作状态（0：停用，1：启用）", example = "1")
    @NotNull(message = "工作状态不能为空")
    private Integer workStatus;
}
