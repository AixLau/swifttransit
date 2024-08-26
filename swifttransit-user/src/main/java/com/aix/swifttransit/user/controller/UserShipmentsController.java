package com.aix.swifttransit.user.controller;


import com.aix.swifttransit.user.dto.PopularItemsDTO;
import com.aix.swifttransit.user.service.UserShipmentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户寄递记录表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@RestController
@RequestMapping("/user-shipments")
@Tag(name = "热门寄递", description = "获取热门寄递的物品列表")
@RequiredArgsConstructor
public class UserShipmentsController {

    private final UserShipmentsService userShipmentsService;

    @Operation(summary = "获取热门寄递物品", description = "显示寄送物品数量排名前6的物品名称和类型")
    @GetMapping("/popular")
    public List<PopularItemsDTO> getPopularItems() {
        return userShipmentsService.getTopPopularItems();
    }

}
