package com.aix.swifttransit.user.controller;


import com.aix.swifttransit.user.dto.AddressAddOrUpdateDTO;
import com.aix.swifttransit.user.entity.Address;
import com.aix.swifttransit.user.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 存储用户地址信息的表，包括发货和收货地址 前端控制器
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Tag(name = "地址管理", description = "用户地址管理接口")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "添加或修改地址", description = "添加一个新的地址")
    @PostMapping("")
    public void addAddress(@RequestBody @Valid AddressAddOrUpdateDTO addressDTO) {
        this.addressService.saveOrUpdateAddress(addressDTO);
    }

    @Operation(summary = "用户地址列表", description = "通过用户id查询用户的地址列表")
    @GetMapping("/{id}")
    public List<Address> getAddressList(
            @Parameter(name = "id", description = "用户id")
            @PathVariable String id) {
        return this.addressService.getAddressListByUserId(id);
    }

    @Operation(summary = "批量删除用户地址", description = "通过地址id批量删除地址")
    @DeleteMapping()
    public void deleteAddress(@RequestBody List<Long> ids) {
        this.addressService.removeBatchByIds(ids);
    }

    @Operation(summary = "用户默认发货地址", description = "通过用户id查询用户的默认发货地址")
    @GetMapping("/send/{id}")
    public Address getSendAddress(
            @Parameter(name = "id", description = "用户id")
            @PathVariable String id) {
        return this.addressService.getSendAddressByUserId(id);
    }

    @Operation(summary = "用户默认收货地址", description = "通过用户id查询用户的默认收货地址")
    @GetMapping("/send/{id}")
    public Address getReceiveAddress(
            @Parameter(name = "id", description = "用户id")
            @PathVariable String id) {
        return this.addressService.getReceiveAddressByUserId(id);
    }


}
