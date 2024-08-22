package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.dto.CarriageDTO;
import com.aix.swifttransit.admin.entity.Carriage;
import com.aix.swifttransit.admin.mapper.CarriageMapper;
import com.aix.swifttransit.admin.service.CarriageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 运费模板表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@Service
public class CarriageServiceImpl extends ServiceImpl<CarriageMapper, Carriage> implements CarriageService {

    @Override
    public List<Carriage> findCarriagesByType(String templateType) {
        if (ObjectUtils.isEmpty(templateType)) {
            return this.list();
        }
        return this.lambdaQuery().eq(Carriage::getTemplateType, templateType).list();
    }

    @Override
    public void createOrUpdateCarriage(CarriageDTO carriageDTO) {
        // 判断模版是否存在，不存在直接新增
        List<Carriage> carriageList = this.lambdaQuery().eq(Carriage::getTemplateType, carriageDTO.getTemplateType())
                .eq(Carriage::getTransportType, carriageDTO.getTransportType()).list();
        if (ObjectUtils.isEmpty(carriageList)) {
            Carriage carriage = new Carriage();
            BeanUtils.copyProperties(carriageDTO, carriage);
            this.saveOrUpdate(carriage);
            return; // 后续操作不执行
        }
        // 判断是否为经济区模版和id是否存在，不是经济区并且不存在id 是新增操作抛出异常
        if (!Objects.equals(carriageDTO.getTemplateType(), 3) && ObjectUtils.isEmpty(carriageDTO.getId())) {
            throw new RuntimeException("模版已存在，请勿重复添加");
        }
        // 将关联城市排序
        String associatedCity = Arrays.stream(carriageDTO.getAssociatedCity().split(","))
                .sorted()
                .collect(Collectors.joining(","));

        // 判断关联城市是否存在
        boolean hasExactMatch = carriageList.stream().filter(carriage ->
                        // 排除掉自己
                        !Objects.equals(carriage.getId(), carriageDTO.getId()))
                .anyMatch(carriage -> Objects.equals(associatedCity, carriage.getAssociatedCity()));

        if (hasExactMatch) {
            // 存在重复城市，抛出异常
            throw new RuntimeException("关联城市重复，不能重复添加");
        }
        // 保存或更新模板
        Carriage carriage = new Carriage();
        BeanUtils.copyProperties(carriageDTO, carriage);
        carriage.setAssociatedCity(associatedCity);
        this.saveOrUpdate(carriage);
    }

    @Override
    public Carriage getCarriageDetailById(Long id) {
        Carriage carriage = this.getById(id);
        if (ObjectUtils.isEmpty(carriage)) throw new RuntimeException("模版不存在");
        return carriage;
    }
}
