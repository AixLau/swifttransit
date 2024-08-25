package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.dto.DriverUpdateDTO;
import com.aix.swifttransit.admin.entity.Driver;
import com.aix.swifttransit.admin.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 司机表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
@Tag(name = "司机管理", description = "司机管理的API")
public class DriverController {

    private final DriverService driverService;

    @Operation(summary = "搜索司机", description = "根据姓名、账号、手机号和机构ID搜索司机")
    @GetMapping("/search")
    public List<Driver> searchDrivers(
            @Parameter(description = "搜索关键字，用于模糊匹配快递员账号")
            @RequestParam(required = false) String account,
            @Parameter(description = "搜索关键字，用于模糊匹配快递员姓名")
            @RequestParam(required = false) String name,
            @Parameter(description = "搜索关键字，用于模糊匹配快递员手机号")
            @RequestParam(required = false) String phone,
            @Parameter(description = "所属机构ID，用于过滤快递员所属的机构", example = "1")
            @RequestParam(required = false) Long organizationId) {
        return driverService.searchDrivers(account, name, phone, organizationId);
    }

    @Operation(summary = "安排车辆", description = "为司机绑定车辆")
    @PostMapping("/{driverId}/assign-truck/{truckId}")
    public void assignTruck(
            @Parameter(description = "司机ID") @PathVariable Long driverId,
            @Parameter(description = "车辆ID") @PathVariable Long truckId) {
        driverService.assignTruckToDriver(driverId, truckId);
    }

    @Operation(summary = "更新司机信息", description = "更新司机的可编辑信息（账号、姓名、手机号不可编辑）")
    @PutMapping("")
    public void updateDriver(
            @RequestBody @Valid DriverUpdateDTO driverUpdateDTO) {
        driverService.updateDriver(driverUpdateDTO);
    }
}
