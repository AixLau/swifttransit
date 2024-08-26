package com.aix.swifttransit.user.controller;


import com.aix.swifttransit.user.entity.Items;
import com.aix.swifttransit.user.service.ItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 物品信息表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@RestController
@RequestMapping("/items")
@Tag(name = "物品信息管理")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemsService itemsService;

    @Operation(summary = "搜索物品")
    @GetMapping("/{keyword}")
    public List<Items> searchItems(@PathVariable String keyword) {
        return this.itemsService.searchItems(keyword);
    }
}
