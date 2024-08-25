package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.dto.DriverUpdateDTO;
import com.aix.swifttransit.admin.entity.Driver;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 司机表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
public interface DriverService extends IService<Driver> {

    /**
     * 根据姓名、账号、手机号和机构ID搜索司机
     *
     * @param account        快递员账号
     * @param name           快递员姓名
     * @param phone          快递员手机号
     * @param organizationId 快递员所属机构id
     * @return 司机列表
     */
    List<Driver> searchDrivers(String account, String name, String phone, Long organizationId);

    /**
     * 为司机绑定车辆
     *
     * @param driverId 司机id
     * @param truckId  车辆id
     */
    void assignTruckToDriver(Long driverId, Long truckId);

    /**
     * 更新司机的可编辑信息（账号、姓名、手机号不可编辑）
     *
     * @param driverUpdateDTO 司机更新数据传输对象
     */
    void updateDriver(DriverUpdateDTO driverUpdateDTO);
}
