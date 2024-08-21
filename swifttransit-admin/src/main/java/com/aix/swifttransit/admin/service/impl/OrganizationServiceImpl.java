package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.dto.OrganizationUpdateDTO;
import com.aix.swifttransit.admin.entity.Organization;
import com.aix.swifttransit.admin.mapper.OrganizationMapper;
import com.aix.swifttransit.admin.service.OrganizationService;
import com.aix.swifttransit.admin.vo.OrganizationTreeNode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构信息表，存储机构的基本信息 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-20
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<OrganizationTreeNode> getOrganizationTree() {
        // 1. 查询所有的机构信息
        List<Organization> organizationsList = this.list();

        // 2. 将所有机构信息转换为树形节点
        List<OrganizationTreeNode> allNodeList = organizationsList.stream()
                .map(this::convertToTreeNode)
                .toList();

        // 3. 构建树形结构
        return allNodeList.stream()
                .filter(node -> node.getParentId() == 0)
                .peek(node -> buildTree(node, allNodeList))
                .toList();
    }


    @Override
    public List<Organization> searchOrganizations(String keyword) {
        return this.lambdaQuery()
                .like(Organization::getName, keyword)
                .list();
    }

    @Override
    public Organization getOrganizationById(Long id) {
        return this.getById(id);
    }

    @Override
    public void updateOrganization(OrganizationUpdateDTO organizationUpdateDTO) {
        this.lambdaUpdate().set(Organization::getAddress, organizationUpdateDTO.getAddress())
                .set(Organization::getContactName, organizationUpdateDTO.getContactName())
                .set(Organization::getContactPhone, organizationUpdateDTO.getPrincipalPhone())
                .eq(Organization::getId, organizationUpdateDTO.getId())
                .update();
    }

    /**
     * 将 OrganizationInfo 实体转换为 OrganizationTreeNode 对象
     *
     * @param org Organization 实体对象
     * @return OrganizationTreeNode 树形节点对象
     */
    private OrganizationTreeNode convertToTreeNode(Organization org) {
        OrganizationTreeNode node = new OrganizationTreeNode();
        node.setId(org.getId());
        node.setName(org.getName());
        node.setType(org.getType());
        node.setParentId(org.getParentId());
        node.setChildren(new ArrayList<>());  // 初始化子节点列表
        return node;
    }

    /**
     * 递归构建树形结构
     */
    public void buildTree(OrganizationTreeNode parentNode, List<OrganizationTreeNode> allNodes) {
        List<OrganizationTreeNode> children = allNodes.stream()
                .filter(node -> parentNode.getId().equals(node.getParentId()))
                .toList();

        // 将子节点加入父节点的children列表中
        parentNode.getChildren().addAll(children);

        // 递归处理每个子节点
        children.forEach(childNode -> buildTree(childNode, allNodes));
    }
}
