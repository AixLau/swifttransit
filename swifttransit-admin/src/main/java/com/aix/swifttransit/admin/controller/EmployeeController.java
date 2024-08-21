package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.entity.Employee;
import com.aix.swifttransit.admin.service.EmployeeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "员工管理", description = "用于管理员工信息的API")
public class EmployeeController {

    private final EmployeeService employeeService;


    @ApiOperationSupport(order = 1)
    @Operation(summary = "分页查询员工信息", description = "根据关键字进行员工信息的分页查询")
    @GetMapping("/page")
    public IPage<Employee> getEmployeePage(
            @Parameter(description = "当前页码，默认为 1", example = "1")
            @RequestParam(defaultValue = "1") int pageNo,
            @Parameter(description = "每页显示的记录数，默认为 10", example = "10")
            @RequestParam(defaultValue = "10") int pageSize,
            @Parameter(description = "机构ID，用于精确查询特定机构下的员工", example = "1")
            @RequestParam(required = false) Long organizationId,
            @Parameter(description = "搜索关键字，用于模糊匹配员工姓名或手机号", example = "张三")
            @RequestParam(required = false)
            String keyword) {
        return this.employeeService.getEmployeePage(pageNo, pageSize, organizationId, keyword);
    }
}
