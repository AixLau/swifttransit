package com.aix.swifttransit.user.service;

import com.aix.swifttransit.user.entity.Items;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 物品信息表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
public interface ItemsService extends IService<Items> {

    /**
     * 模糊查询物品
     *
     * @param keyword 查询关键字
     * @return 物品列表
     */
    List<Items> searchItems(String keyword);
}
