package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.entity.Courier;
import com.aix.swifttransit.admin.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 快递员信息表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
@RestController
@RequestMapping("/courier")
@RequiredArgsConstructor
@Tag(name = "快递员管理", description = "快递员管理的API")
public class CourierController {

    private final CourierService courierService;

    @Operation(summary = "获取快递员列表", description = "根据搜索条件获取快递员列表")
    @GetMapping("/list")
    public List<Courier> listCouriers(
            @Parameter(description = "搜索关键字，用于模糊匹配快递员账号")
            @RequestParam(required = false) String account,
            @Parameter(description = "搜索关键字，用于模糊匹配快递员姓名")
            @RequestParam(required = false) String name,
            @Parameter(description = "搜索关键字，用于模糊匹配快递员手机号")
            @RequestParam(required = false) String phone,
            @Parameter(description = "所属机构ID，用于过滤快递员所属的机构", example = "1")
            @RequestParam(required = false) Long organizationId) {
        return courierService.searchCouriers(account, name, phone, organizationId);
    }

    @Operation(summary = "获取快递员详情", description = "根据ID获取快递员详情")
    @GetMapping("/detail/{id}")
    public Courier getCourierDetail(@Parameter(description = "快递员ID，用于唯一标识快递员", example = "1") @PathVariable Long id) {
        return courierService.getById(id);
    }
}
