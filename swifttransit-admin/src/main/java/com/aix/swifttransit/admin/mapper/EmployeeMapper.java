package com.aix.swifttransit.admin.mapper;

import com.aix.swifttransit.admin.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
