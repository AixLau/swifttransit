package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.dto.CarriageDTO;
import com.aix.swifttransit.admin.entity.Carriage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 运费模板表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
public interface CarriageService extends IService<Carriage> {

    /**
     * 查询运费模板列表
     *
     * @param templateType 模板类型，可为null。如果指定则按类型查询，否则返回所有模板
     * @return 返回符合条件的运费模板列表
     */
    List<Carriage> findCarriagesByType(String templateType);

    /**
     * 新增或更新运费模板
     *
     * @param carriageDTO 运费模板数据传输对象
     */
    void createOrUpdateCarriage(CarriageDTO carriageDTO);

    /**
     * 根据ID查询运费模板详情
     *
     * @param id 运费模板ID
     * @return 运费模板详细信息
     */
    Carriage getCarriageDetailById(Long id);
}
