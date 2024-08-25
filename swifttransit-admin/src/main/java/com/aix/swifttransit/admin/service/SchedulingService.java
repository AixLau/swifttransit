package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.entity.Scheduling;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 排班管理表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
public interface SchedulingService extends IService<Scheduling> {

    /**
     * 根据搜索条件查询排班信息
     *
     * @return 排班信息列表
     */
    List<Scheduling> searchSchedules(String employeeName, Integer workMode, String scheduleDate);
}
