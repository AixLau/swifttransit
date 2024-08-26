package com.aix.swifttransit.user.service.impl;

import com.aix.swifttransit.user.dto.PopularItemsDTO;
import com.aix.swifttransit.user.entity.UserShipments;
import com.aix.swifttransit.user.mapper.UserShipmentsMapper;
import com.aix.swifttransit.user.service.UserShipmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户寄递记录表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@Service
public class UserShipmentsServiceImpl extends ServiceImpl<UserShipmentsMapper, UserShipments> implements UserShipmentsService {

    @Override
    @Cacheable(value = "popularItemsCache", key = "'topPopularItems'")
    public List<PopularItemsDTO> getTopPopularItems() {
        // 获取当前时间和一个月前的时间，作为时间范围过滤
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusMonths(1);

        // 查询热门寄递物品，并按时间范围过滤
        List<UserShipments> shipmentList = this.lambdaQuery()
                .select(UserShipments::getItemName, UserShipments::getItemType)
                .between(UserShipments::getShipmentDate, startTime, endTime)
                .groupBy(UserShipments::getItemName, UserShipments::getItemType)
                .last("ORDER BY COUNT(*) DESC LIMIT 6").list();

        // 执行查询并转换结果为 PopularItemsDTO
        List<PopularItemsDTO> list = shipmentList.stream()
                .map(shipment -> {
                    PopularItemsDTO dto = new PopularItemsDTO();
                    dto.setItemName(shipment.getItemName());
                    dto.setItemType(shipment.getItemType());
                    return dto;
                })
                .toList();
        return list;
    }
}
