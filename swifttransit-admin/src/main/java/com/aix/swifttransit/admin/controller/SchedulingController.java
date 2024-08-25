package com.aix.swifttransit.admin.controller;


import com.aix.swifttransit.admin.entity.Scheduling;
import com.aix.swifttransit.admin.service.SchedulingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 排班管理表 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
@RestController
@RequestMapping("/scheduling")
@RequiredArgsConstructor
@Tag(name = "排班管理", description = "排班管理相关的操作")
public class SchedulingController {

    private final SchedulingService schedulingService;

    @Operation(summary = "搜索排班信息", description = "根据条件搜索排班信息")
    @GetMapping("/search")
    public List<Scheduling> searchSchedules(
            @Parameter(description = "员工名称") @RequestParam(required = false) String employeeName,
            @Parameter(description = "工作模式（1-礼拜制，2-连续制）") @RequestParam(required = false) Integer workMode,
            @Parameter(description = "排班日期") @RequestParam(required = false) String scheduleDate) {

        return schedulingService.searchSchedules(employeeName, workMode, scheduleDate);
    }

    @Operation(summary = "添加排班", description = "添加新的排班记录")
    @PostMapping("/add")
    public void addSchedule(@RequestBody Scheduling scheduling) {
        schedulingService.save(scheduling);
    }

    @Operation(summary = "更新排班", description = "更新已有的排班记录")
    @PutMapping("/update")
    public void updateSchedule(@RequestBody Scheduling scheduling) {
        schedulingService.updateById(scheduling);
    }

    @Operation(summary = "删除排班", description = "根据ID删除排班记录")
    @DeleteMapping("/delete/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        schedulingService.removeById(id);
    }
}
