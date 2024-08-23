package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.entity.TruckType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 车辆类型表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-23
 */
public interface TruckTypeService extends IService<TruckType> {

    /**
     * 获取分页的车辆类型数据
     *
     * @param current         页码
     * @param pageSize        页尺寸
     * @param allowableLoad   允许负载
     * @param allowableVolume 容许体积
     * @param id              车型id
     * @param name            车型名称
     * @return 车辆类型分页数据
     */
    IPage<TruckType> getTruckTypePage(int current, int pageSize, String allowableLoad, String allowableVolume, Long id, String name);
}
