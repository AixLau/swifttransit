package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.dto.OrganizationUpdateDTO;
import com.aix.swifttransit.admin.entity.Organization;
import com.aix.swifttransit.admin.vo.OrganizationTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 机构信息表，存储机构的基本信息 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-20
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 获取机构的树形结构。
     *
     * @return 包含机构信息的树形结构列表。
     */
    List<OrganizationTreeNode> getOrganizationTree();

    /**
     * 模糊搜索机构。
     *
     * @param keyword 搜索关键字，用于模糊匹配机构名称、地址、负责人等信息。
     * @return 符合条件的机构列表。
     */
    List<Organization> searchOrganizations(String keyword);

    /**
     * 根据机构ID获取机构的详细信息。
     *
     * @param id 机构ID。
     * @return 机构的详细信息。
     */
    Organization getOrganizationById(Long id);

    void updateOrganization(OrganizationUpdateDTO organizationUpdateDTO);
}
