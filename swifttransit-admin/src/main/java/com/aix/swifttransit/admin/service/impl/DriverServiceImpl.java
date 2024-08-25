package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.Truck;
import com.aix.swifttransit.admin.dto.DriverUpdateDTO;
import com.aix.swifttransit.admin.entity.Driver;
import com.aix.swifttransit.admin.mapper.DriverMapper;
import com.aix.swifttransit.admin.mapper.TruckMapper;
import com.aix.swifttransit.admin.service.DriverService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 司机表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
@Service
@RequiredArgsConstructor
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {

    private final TruckMapper truckMapper;

    @Override
    public List<Driver> searchDrivers(String account, String name, String phone, Long organizationId) {
        return this.lambdaQuery().like(ObjectUtils.isNotEmpty(account), Driver::getAccount, account)
                .like(ObjectUtils.isNotEmpty(name), Driver::getName, name)
                .like(ObjectUtils.isNotEmpty(phone), Driver::getPhone, phone)
                .eq(ObjectUtils.isNotEmpty(organizationId), Driver::getOrganizationId, organizationId)
                .list();
    }

    @Override
    public void assignTruckToDriver(Long driverId, Long truckId) {
        // 确保每个司机只可绑定一辆车
        Driver driver = this.getById(driverId);
        Truck truck = truckMapper.selectById(truckId);

        if (driver == null || truck == null) {
            throw new RuntimeException("司机或车辆不存在");
        }

        if (!driver.getPreferredTruckTypes().contains(truck.getTruckType())) {
            throw new RuntimeException("司机不擅长此类型车辆");
        }

        // 检查车辆是否已绑定两名司机
        if (truck.getDriverNum() >= 2) {
            throw new RuntimeException("该车辆已绑定两名司机，无法继续绑定");
        }

        // 检查司机是否已经绑定了其他车辆，如果是，则先解除绑定。
        Truck currentTruck = truckMapper.selectOne(new LambdaQueryWrapper<Truck>()
                .like(Truck::getDriverIds, driverId));
        if (currentTruck != null) {
            // 解除旧车辆绑定
            List<String> currentDriverIds = Arrays.asList(currentTruck.getDriverIds().split(","));
            currentDriverIds.remove(driverId.toString());
            currentTruck.setDriverIds(String.join(",", currentDriverIds));
            currentTruck.setDriverNum(currentTruck.getDriverNum() - 1); // 更新司机数量
            truckMapper.updateById(currentTruck);
        }

        // 绑定司机到车辆，并更新相关信息。
        List<String> newDriverIds = new ArrayList<>(Arrays.asList(truck.getDriverIds().split(",")));
        newDriverIds.add(driverId.toString());
        truck.setDriverIds(String.join(",", newDriverIds));
        truck.setDriverNum(truck.getDriverNum() + 1); // 更新司机数量
        truckMapper.updateById(truck);
    }

    @Override
    public void updateDriver(DriverUpdateDTO driverUpdateDTO) {
        Driver existingDriver = this.getById(driverUpdateDTO.getId());
        if (existingDriver == null) {
            throw new RuntimeException("司机不存在，ID: " + driverUpdateDTO.getId());
        }

        Driver driverToUpdate = new Driver();
        BeanUtils.copyProperties(driverUpdateDTO, driverToUpdate, "account", "name", "phone");
        driverToUpdate.setId(existingDriver.getId());
        boolean flag = this.updateById(driverToUpdate);

        if (!flag) {
            throw new RuntimeException("更新司机信息失败，ID: " + driverUpdateDTO.getId());
        }
    }
}