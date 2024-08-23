package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.dto.TruckTypeDTO;
import com.aix.swifttransit.admin.entity.TruckType;
import com.aix.swifttransit.admin.service.TruckTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 车辆类型表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-23
 */
@RestController
@RequestMapping("/truck-type")
@RequiredArgsConstructor
@Tag(name = "车型管理", description = "管理车辆类型的API")
public class TruckTypeController {

    private final TruckTypeService truckTypeService;

    @ApiOperationSupport(order = 1)
    @Operation(summary = "添加车辆类型", description = "添加新的车辆类型")
    @PostMapping
    public void addTruckType(@RequestBody @Valid TruckTypeDTO truckTypeDTO) {
        TruckType truckType = new TruckType();
        BeanUtils.copyProperties(truckTypeDTO, truckType);
        this.truckTypeService.save(truckType);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取车辆类型分页数据", description = "根据条件获取车辆类型的分页数据")
    @GetMapping("/page")
    public IPage<TruckType> getTruckTypePage(
            @RequestParam int current,
            @RequestParam int pageSize,
            @RequestParam(required = false) String allowableLoad,
            @RequestParam(required = false) String allowableVolume,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {
        return truckTypeService.getTruckTypePage(current, pageSize, allowableLoad, allowableVolume, id, name);
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取车辆类型信息列表", description = "获取所有车辆类型的简单列表")
    @GetMapping("/simple")
    public List<TruckType> getAllTruckTypeList() {
        return this.truckTypeService.list();
    }

    @ApiOperationSupport(order = 4)
    @Operation(summary = "获取车辆类型详情", description = "根据车辆类型ID获取详细信息")
    @GetMapping("/{id}")
    public TruckType getTruckTypeDetail(@Parameter(description = "车辆类型ID", example = "1") @PathVariable Long id) {
        return this.truckTypeService.getById(id);
    }

    @ApiOperationSupport(order = 5)
    @Operation(summary = "更新车辆类型", description = "根据车辆类型ID更新车辆类型信息")
    @PutMapping("")
    public void updateTruckType(@RequestBody @Valid TruckTypeDTO truckTypeDTO) {
        TruckType truckType = new TruckType();
        BeanUtils.copyProperties(truckTypeDTO, truckType);
        this.truckTypeService.updateById(truckType);
    }

    @ApiOperationSupport(order = 6)
    @Operation(summary = "删除车辆类型", description = "根据车辆类型ID删除车辆类型")
    @DeleteMapping("/{id}")
    public void deleteTruckType(@Parameter(description = "车辆类型ID", required = true, example = "1") @PathVariable Long id) {
        this.truckTypeService.removeById(id);
    }
}
