package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.Truck;
import com.aix.swifttransit.admin.mapper.TruckMapper;
import com.aix.swifttransit.admin.service.TruckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author lushiwu
 * @description 针对表【truck(车辆信息表)】的数据库操作Service实现
 * @createDate 2024-08-24 12:45:32
 */
@Service
public class TruckServiceImpl extends ServiceImpl<TruckMapper, Truck>
        implements TruckService {

    @Override
    public List<Truck> getWorkingTrucks() {
        return this.lambdaQuery().eq(Truck::getWorkStatus, 1).list();
    }

    @Override
    public List<Truck> getUnworkingTrucks() {
        return this.lambdaQuery().eq(Truck::getWorkStatus, 0).list();
    }
}




