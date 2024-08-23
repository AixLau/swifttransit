package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.entity.TruckType;
import com.aix.swifttransit.admin.mapper.TruckTypeMapper;
import com.aix.swifttransit.admin.service.TruckTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 车辆类型表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-23
 */
@Service
public class TruckTypeServiceImpl extends ServiceImpl<TruckTypeMapper, TruckType> implements TruckTypeService {

    @Override
    public IPage<TruckType> getTruckTypePage(int page, int pageSize, String allowableLoad, String allowableVolume, Long id, String name) {
        IPage<TruckType> iPage = new Page<>(page, pageSize);
        return this.lambdaQuery().func(i -> {
            if (!ObjectUtils.isEmpty(allowableLoad)) {
                i.eq(TruckType::getAllowableLoad, allowableLoad);
            }
            if (!ObjectUtils.isEmpty(allowableVolume)) {
                i.eq(TruckType::getAllowableVolume, allowableVolume);
            }
            if (!ObjectUtils.isEmpty(id)) {
                i.eq(TruckType::getId, id);
            }
            if (!ObjectUtils.isEmpty(name)) {
                i.like(TruckType::getName, name);
            }
        }).page(iPage);
    }
}