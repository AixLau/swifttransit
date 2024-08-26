package com.aix.swifttransit.user.service;

import com.aix.swifttransit.user.dto.AddressAddOrUpdateDTO;
import com.aix.swifttransit.user.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 存储用户地址信息的表，包括发货和收货地址 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
public interface AddressService extends IService<Address> {

    void saveOrUpdateAddress(AddressAddOrUpdateDTO addressDTO);

    List<Address> getAddressListByUserId(Long id);

    Address getSendAddressByUserId(Long id);

    Address getReceiveAddressByUserId(Long id);

    List<Address> searchAddress(Long userId, String keyword);
}
