package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.Truck;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lushiwu
 * @description 针对表【truck(车辆信息表)】的数据库操作Service
 * @createDate 2024-08-24 12:45:32
 */
public interface TruckService extends IService<Truck> {
    /**
     * 获取已启用状态的车辆列表
     */
    List<Truck> getWorkingTrucks();

    /**
     * 获取已停用状态的车辆列
     *
     * @return
     */
    List<Truck> getUnworkingTrucks();
}
