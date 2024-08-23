package com.aix.swifttransit.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * TruckTypeDTO - 车辆类型数据传输对象
 * </p>
 * <p>
 * 用于在控制器层和服务层之间传递车辆类型数据。
 *
 * @author aix
 * @since 2024-08-21
 */
@Data
@Schema(description = "车辆类型数据传输对象")
public class TruckTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "车型ID")
    private Long id;

    @NotBlank(message = "车型名称不能为空")
    @Schema(description = "车型名称", example = "小型货车")
    private String name;

    @NotNull(message = "车型长度不能为空")
    @DecimalMin(value = "0.01", message = "车型长度必须大于0")
    @Schema(description = "车型长度（单位：米）", example = "4.5")
    private Double measureLong;

    @NotNull(message = "车型宽度不能为空")
    @DecimalMin(value = "0.01", message = "车型宽度必须大于0")
    @Schema(description = "车型宽度（单位：米）", example = "1.8")
    private Double measureWidth;

    @NotNull(message = "车型高度不能为空")
    @DecimalMin(value = "0.01", message = "车型高度必须大于0")
    @Schema(description = "车型高度（单位：米）", example = "2.5")
    private Double measureHigh;

    @NotNull(message = "准载重量不能为空")
    @DecimalMin(value = "0.1", message = "准载重量必须大于0")
    @Schema(description = "准载重量（单位：吨）", example = "3.0")
    private Double allowableLoad;

    @NotNull(message = "准载体积不能为空")
    @DecimalMin(value = "0.1", message = "准载体积必须大于0")
    @Schema(description = "准载体积（单位：立方米）", example = "12.0")
    private Double allowableVolume;

    @NotNull(message = "车型数量不能为空")
    @Min(value = 1, message = "车型数量必须大于0")
    @Schema(description = "车型数量", example = "10")
    private Integer num;

    @Schema(description = "状态 0：禁用 1：正常", example = "1")
    private Integer status;
}
