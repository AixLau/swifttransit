package com.aix.swifttransit.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 机构信息表，存储机构的基本信息
 * </p>
 *
 * @author aix
 * @since 2024-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("organization")
@Tag(name = "机构信息实体类，存储机构的基本信息")
public class Organization implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    @NotBlank(message = "上级机构不能为空")
    @TableField("parent_id")
    @Schema(description = "父机构ID，指向上级机构")
    private Long parentId;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    @Size(max = 255, message = "机构名称不能超过255个字符")
    @TableField("name")
    @Schema(description = "机构名称")
    private String name;

    /**
     * 机构类型
     */
    @NotBlank(message = "机构类型不能为空")
    @Pattern(regexp = "分公司|一级转运中心|二级转运中心|网点", message = "机构类型不符合要求")
    @TableField("type")
    @Schema(description = "机构类型", allowableValues = {"分公司", "一级转运中心", "二级转运中心", "网点"})
    private String type;

    /**
     * 机构地址（省市区三级筛选加街道）
     */
    @NotBlank(message = "机构地址不能为空")
    @Size(max = 255, message = "机构地址不能超过255个字符")
    @TableField("address")
    @Schema(description = "机构地址（省市区三级筛选加街道）")
    private String address;

    /**
     * 机构负责人姓名（仅允许中文，最多5个汉字）
     */
    @NotBlank(message = "机构负责人姓名不能为空")
    @Size(max = 5, message = "机构负责人姓名不能超过5个汉字")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,5}$", message = "机构负责人姓名只能包含中文字符")
    @TableField("principal_name")
    @Schema(description = "机构负责人姓名（仅允许中文，最多5个汉字）")
    private String principalName;

    /**
     * 机构负责人电话（11位手机号）
     */
    @NotBlank(message = "机构负责人电话不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "机构负责人电话必须为11位数字")
    @TableField("principal_phone")
    @Schema(description = "机构负责人电话（11位手机号）")
    private String principalPhone;

    /**
     * 机构对接人姓名（仅允许中文，最多5个汉字）
     */
    @NotBlank(message = "机构对接人姓名不能为空")
    @Size(max = 5, message = "机构对接人姓名不能超过5个汉字")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,5}$", message = "机构对接人姓名只能包含中文字符")
    @TableField("contact_name")
    @Schema(description = "机构对接人姓名（仅允许中文，最多5个汉字）")
    private String contactName;

    /**
     * 机构对接人电话（11位手机号）
     */
    @NotBlank(message = "机构对接人电话不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "机构对接人电话必须为11位数字")
    @TableField("contact_phone")
    @Schema(description = "机构对接人电话（11位手机号）")
    private String contactPhone;

    /**
     * 机构经度（精确到小数点后六位）
     */
    @NotBlank(message = "经度不能为空")
    @Digits(integer = 3, fraction = 6, message = "经度格式不正确")
    @DecimalMin(value = "0.0", inclusive = false, message = "经度必须大于0")
    @DecimalMax(value = "180.0", inclusive = true, message = "经度不能超过180度")
    @TableField("longitude")
    @Schema(description = "机构经度（精确到小数点后六位）")
    private BigDecimal longitude;

    /**
     * 机构纬度（精确到小数点后六位）
     */
    @NotBlank(message = "纬度不能为空")
    @Digits(integer = 2, fraction = 6, message = "纬度格式不正确")
    @DecimalMin(value = "0.0", inclusive = false, message = "纬度必须大于0")
    @DecimalMax(value = "90.0", inclusive = true, message = "纬度不能超过90度")
    @TableField("latitude")
    @Schema(description = "机构纬度（精确到小数点后六位）")
    private BigDecimal latitude;

    /**
     * 纬度方向（N: 北纬, S: 南纬）
     */
    @NotBlank(message = "纬度方向不能为空")
    @Pattern(regexp = "N|S", message = "纬度方向只能为N或S")
    @TableField("north_south")
    @Schema(description = "纬度方向（N: 北纬, S: 南纬）", allowableValues = {"N", "S"})
    private String northSouth;

    /**
     * 经度方向（E: 东经, W: 西经）
     */
    @NotBlank(message = "经度方向不能为空")
    @Pattern(regexp = "E|W", message = "经度方向只能为E或W")
    @TableField("east_west")
    @Schema(description = "经度方向（E: 东经, W: 西经）", allowableValues = {"E", "W"})
    private String eastWest;

    /**
     * 逻辑删除 1删除  0未删除
     */
    @TableField("deleted")
    @Schema(description = "逻辑删除标识（1: 删除, 0: 未删除）")
    private Integer deleted;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Data createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Data updateTime;
}
