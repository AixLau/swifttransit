package com.aix.swifttransit.admin.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationUpdateDTO {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 机构地址（省市区三级筛选加街道）
     */
    @NotBlank(message = "机构地址不能为空")
    @Size(max = 255, message = "机构地址不能超过255个字符")
    @TableField("address")
    @Schema(description = "机构地址（省市区三级筛选加街道）")
    private String address;

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
}
