package com.aix.swifttransit.user.service;

import com.aix.swifttransit.user.dto.PopularItemsDTO;
import com.aix.swifttransit.user.entity.UserShipments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户寄递记录表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
public interface UserShipmentsService extends IService<UserShipments> {

    List<PopularItemsDTO> getTopPopularItems();
}
