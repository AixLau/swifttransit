package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.entity.Employee;
import com.aix.swifttransit.admin.entity.Organization;
import com.aix.swifttransit.admin.mapper.EmployeeMapper;
import com.aix.swifttransit.admin.service.EmployeeService;
import com.aix.swifttransit.admin.service.OrganizationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    private final OrganizationService organizationService;

    @Override
    public IPage<Employee> getEmployeePage(int pageNo, int pageSize, Long organizationId, String keyword) {
        Page<Employee> page = new Page<>(pageNo, pageSize);
        return lambdaQuery()
                .func(i -> {
                    if (organizationId != null) {
                        List<Long> organizationIds = getChildOrganizationIds(organizationId);
                        i.in(Employee::getOrganizationId, organizationIds);
                    }
                })
                .func(i -> {
                    if (keyword != null && !keyword.trim().isEmpty()) {
                        i.like(Employee::getName, keyword)
                                .or().like(Employee::getPhone, keyword);
                    }
                }).page(page);
    }

    /**
     * 获取指定机构及其所有子机构的ID列表
     *
     * @param organizationId 目标机构的ID
     * @return 包含目标机构及其所有子机构ID的列表
     */
    public List<Long> getChildOrganizationIds(Long organizationId) {
        // 获取所有机构节点
        List<Organization> allOrganizations = organizationService.list();

        // 将机构节点按parentId分组
        Map<Long, List<Organization>> nodesMap = allOrganizations.stream()
                .collect(Collectors.groupingBy(Organization::getParentId));

        // 递归获取目标机构及其所有子机构ID
        List<Long> result = new ArrayList<>();
        fetchChildIds(organizationId, nodesMap, result);
        return result;
    }

    /**
     * 递归地获取指定机构及其子机构的ID，并将它们加入到结果列表中
     *
     * @param organizationId 目标机构的ID
     * @param nodesMap       按 parentId 分组的机构节点Map
     * @param result         用于存储机构ID的结果列表
     */
    private void fetchChildIds(Long organizationId, Map<Long, List<Organization>> nodesMap, List<Long> result) {
        // 将当前机构ID加入结果列表
        result.add(organizationId);
        // 获取当前机构的子机构列表
        List<Organization> children = nodesMap.get(organizationId);
        // 如果子机构列表不为空，递归处理每个子机构
        if (children != null && !children.isEmpty()) {
            for (Organization child : children) {
                fetchChildIds(child.getId(), nodesMap, result);
            }
        }
    }
}
