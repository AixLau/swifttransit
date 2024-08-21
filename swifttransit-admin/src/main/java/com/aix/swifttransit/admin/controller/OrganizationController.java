package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.dto.OrganizationUpdateDTO;
import com.aix.swifttransit.admin.entity.Organization;
import com.aix.swifttransit.admin.service.OrganizationService;
import com.aix.swifttransit.admin.vo.OrganizationTreeNode;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 机构信息表，存储机构的基本信息 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-20
 */
@RestController
@RequestMapping("/organization-info")
@RequiredArgsConstructor
@Tag(name = "机构管理", description = "用于管理机构的API")
public class OrganizationController {

    private final OrganizationService organizationService;

    @ApiOperationSupport()
    @Operation(summary = "获取机构树形结构", description = "返回机构的层级结构树")
    @GetMapping("/tree")
    public List<OrganizationTreeNode> getOrganizationTree() {
        return organizationService.getOrganizationTree();
    }

    @ApiOperationSupport(order = 1)
    @Operation(summary = "模糊搜索机构", description = "根据关键词模糊搜索机构")
    @GetMapping("/search")
    @Valid
    public List<Organization> searchOrganizations(@RequestParam() @Valid @NotBlank(message = "搜索条件不能为空") String keyword) {
        return organizationService.searchOrganizations(keyword);
    }


    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取机构详细信息", description = "根据机构ID获取机构的详细信息")
    @GetMapping("/{id}")
    public Organization getOrganizationById(
            @PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新机构信息", description = "更新机构的详细信息")
    @PutMapping()
    public void updateOrganization(@RequestBody OrganizationUpdateDTO organizationUpdateDTO) {
        this.organizationService.updateOrganization(organizationUpdateDTO);
    }

}
