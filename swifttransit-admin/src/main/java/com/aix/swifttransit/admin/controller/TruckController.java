package com.aix.swifttransit.admin.controller;

import com.aix.swifttransit.admin.Truck;
import com.aix.swifttransit.admin.service.TruckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
@Tag(name = "车辆管理", description = "管理车辆的API")
public class TruckController {

    private final TruckService truckService;

    @Operation(summary = "获取所有车辆", description = "获取车辆的列表")
    @GetMapping("/trucks")
    public List<Truck> getAllTrucks() {
        return truckService.list();
    }

    @Operation(summary = "获取车辆详情", description = "根据车辆ID获取车辆的详细信息")
    @GetMapping("/truck/{id}")
    public Truck getTruckById(@PathVariable Long id) {
        return truckService.getById(id);
    }

    @Operation(summary = "新增车辆", description = "新增车辆信息")
    @PostMapping("/truck")
    public boolean saveTruck(@RequestBody Truck truck) {
        return truckService.save(truck);
    }

    @Operation(summary = "更新车辆信息", description = "根据车辆ID更新车辆信息")
    @PutMapping("/truck/{id}")
    public boolean updateTruck(@PathVariable Long id, @RequestBody Truck truck) {
        truck.setId(id);
        return truckService.updateById(truck);
    }

    @Operation(summary = "删除车辆", description = "根据车辆ID删除车辆信息")
    @DeleteMapping("/truck/{id}")
    public boolean deleteTruck(@PathVariable Long id) {
        return truckService.removeById(id);
    }

    @Operation(summary = "获取启用的车辆", description = "获取已启用状态的车辆列表")
    @GetMapping("/workingTrucks")
    public List<Truck> getWorkingTrucks() {
        return truckService.getWorkingTrucks();
    }

    @Operation(summary = "获取停用的车辆", description = "获取已停用状态的车辆列表")
    @GetMapping("/unWorkingTrucks")
    public List<Truck> getUnworkingTrucks() {
        return truckService.getUnworkingTrucks();
    }
}
