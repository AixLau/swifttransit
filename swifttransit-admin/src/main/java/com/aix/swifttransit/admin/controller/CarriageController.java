package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.dto.CarriageDTO;
import com.aix.swifttransit.admin.entity.Carriage;
import com.aix.swifttransit.admin.service.CarriageService;
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
 * 运费模板表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-21
 */
@RestController
@RequestMapping("/carriage")
@RequiredArgsConstructor
@Tag(name = "运费模板管理", description = "管理运费模板的API")
public class CarriageController {

    private final CarriageService carriageService;

    @ApiOperationSupport(order = 5)
    @Operation(summary = "新增或更新运费模板", description = "根据传入的数据，新增或更新运费模板，确保模板的唯一性")
    @PostMapping("/save")
    public void saveCarriage(@RequestBody @Valid CarriageDTO carriageDTO) {
        carriageService.createOrUpdateCarriage(carriageDTO);
    }

    @ApiOperationSupport(order = 1)
    @Operation(summary = "编辑运费模板", description = "根据运费模板ID编辑模板信息")
    @PutMapping("")
    public void updateCarriage(@RequestBody @Valid CarriageDTO carriageDTO) {
        Carriage carriage = new Carriage();
        BeanUtils.copyProperties(carriageDTO, carriage);
        carriageService.updateById(carriage);
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除运费模板", description = "根据运费模板ID删除模板信息")
    @DeleteMapping("/{id}")
    public void deleteCarriage(@PathVariable Long id) {
        carriageService.removeById(id);
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "运费模板列表", description = "根据模板类型查询运费模板列表，如果不指定类型则返回所有模板")
    @GetMapping("/list")

    public List<Carriage> carriageList(
            @Parameter(description = "模板类型（1-同城寄 2-省内寄 3-经济区互寄 4-跨省）", example = "1")
            @RequestParam(required = false) String templateType) {
        return carriageService.findCarriagesByType(templateType);
    }

    @ApiOperationSupport(order = 4)
    @Operation(summary = "查询运费模板详情", description = "根据运费模板ID查询模板的详细信息")
    @GetMapping("/detail/{id}")
    public Carriage getCarriageDetail(
            @Parameter(description = "运费模板ID", example = "1")
            @PathVariable Long id) {
        return carriageService.getCarriageDetailById(id);
    }
}


