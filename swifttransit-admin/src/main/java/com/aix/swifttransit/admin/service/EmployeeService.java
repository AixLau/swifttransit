package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.entity.Employee;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 分页查询员工信息
     *
     * <p>
     * 通过该接口，可以根据指定的页码、每页大小以及可选的关键字对员工信息进行分页查询。
     * 关键字将用于模糊匹配员工的姓名或手机号。
     * </p>
     *
     * @param pageNo         当前页码，默认为 1
     * @param pageSize       每页显示的记录数，默认为 10
     * @param organizationId （可选）机构ID，用于精确查询特定机构下的员工
     * @param keyword        （可选）搜索关键字，用于模糊匹配员工姓名或手机号
     * @return 返回包含分页信息和员工列表的 IPage 对象
     */
    IPage<Employee> getEmployeePage(int pageNo, int pageSize, Long organizationId, String keyword);
}
