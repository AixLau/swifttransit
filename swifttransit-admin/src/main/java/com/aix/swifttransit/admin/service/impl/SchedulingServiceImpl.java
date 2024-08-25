package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.entity.Scheduling;
import com.aix.swifttransit.admin.mapper.SchedulingMapper;
import com.aix.swifttransit.admin.service.SchedulingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 排班管理表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
@Service
public class SchedulingServiceImpl extends ServiceImpl<SchedulingMapper, Scheduling> implements SchedulingService {

    @Override
    public List<Scheduling> searchSchedules(String employeeName, Integer workMode, String scheduleDate) {
        QueryWrapper<Scheduling> queryWrapper = new QueryWrapper<>();
        if (employeeName != null) {
            queryWrapper.like("employee_name", employeeName);  // 根据员工名称模糊查询
        }
        if (workMode != null) {
            queryWrapper.eq("work_mode", workMode);  // 根据工作模式精确查询
        }
        if (scheduleDate != null) {
            queryWrapper.eq("schedule_date", scheduleDate);  // 根据排班日期查询
        }
        return this.list(queryWrapper);  // 返回查询结果
    }
}
