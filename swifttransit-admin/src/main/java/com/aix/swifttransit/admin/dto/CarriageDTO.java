package com.aix.swifttransit.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 运费模板表
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "运费模板数据传输对象")
public class CarriageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运费模板id
     */
    @Schema(description = "运费模板ID", example = "1")
    private Long id;

    /**
     * 模板类型，1-同城寄 2-省内寄 3-经济区互寄 4-跨省
     */
    @Schema(description = "模板类型，1-同城寄 2-省内寄 3-经济区互寄 4-跨省", required = true, example = "1")
    @NotNull(message = "模板类型不能为空")
    private Integer templateType;

    /**
     * 运送类型，1-普快 2-特快
     */
    @Schema(description = "运送类型，1-普快 2-特快", required = true, example = "1")
    @NotNull(message = "运送类型不能为空")
    private Integer transportType;

    /**
     * 关联城市，1-全国 2-京津冀 3-江浙沪 4-川渝 5-黑吉辽
     */
    @Schema(description = "关联城市，如：全国、京津冀、江浙沪、川渝、黑吉辽", required = true, example = "1")
    @NotNull(message = "关联城市不能为空")
    @Size(max = 20, message = "关联城市的名称不能超过20个字符")
    private String associatedCity;

    /**
     * 首重价格
     */
    @Schema(description = "首重价格", required = true, example = "12.5")
    @NotNull(message = "首重价格不能为空")
    @Positive(message = "首重价格必须为正数")
    private Double firstWeight;

    /**
     * 续重价格
     */
    @Schema(description = "续重价格", required = true, example = "5.5")
    @NotNull(message = "续重价格不能为空")
    @Positive(message = "续重价格必须为正数")
    private Double continuousWeight;

    /**
     * 轻抛系数
     */
    @Schema(description = "轻抛系数", required = true, example = "12000")
    @NotNull(message = "轻抛系数不能为空")
    @Positive(message = "轻抛系数必须为正整数")
    private Integer lightThrowingCoefficient;

}
