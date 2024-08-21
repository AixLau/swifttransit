package com.aix.swifttransit.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 机构树形节点 DTO，用于传输树形结构的机构信息。
 */
@Setter
@Getter
@Schema(description = "机构树形节点DTO，表示机构的层级结构")
public class OrganizationTreeNode implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "机构ID")
    private Long id;

    @Schema(description = "上级机构ID")
    private Long parentId;

    @Schema(description = "机构名称")
    private String name;

    @Schema(description = "机构类型")
    private String type;

    @Schema(description = "子机构列表")
    private List<OrganizationTreeNode> children;


}
