package com.aix.swifttransit.user.service.impl;

import com.aix.swifttransit.user.dto.AddressAddOrUpdateDTO;
import com.aix.swifttransit.user.entity.Address;
import com.aix.swifttransit.user.mapper.AddressMapper;
import com.aix.swifttransit.user.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储用户地址信息的表，包括发货和收货地址 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public void saveOrUpdateAddress(AddressAddOrUpdateDTO addressDTO) {
        if (ObjectUtils.isNotEmpty(addressDTO.getSendStatus())) {
            Address address = this.lambdaQuery().eq(Address::getUserId, addressDTO.getUserId()).eq(Address::getSendStatus, 1).one();
            if (ObjectUtils.isNotEmpty(address)) {
                address.setSendStatus(0);
                this.updateById(address);
            }
        } else if (ObjectUtils.isNotEmpty(addressDTO.getReceiveStatus())) {
            Address address = this.lambdaQuery().eq(Address::getUserId, addressDTO.getUserId()).eq(Address::getReceiveStatus, 1).one();
            if (ObjectUtils.isNotEmpty(address)) {
                address.setReceiveStatus(0);
                this.updateById(address);
            }
        } else {
            Address address = new Address();
            BeanUtils.copyProperties(addressDTO, address);
            this.saveOrUpdate(address);
        }
    }

    @Override
    public List<Address> getAddressListByUserId(String id) {
        return this.lambdaQuery().eq(Address::getUserId, id).list();
    }

    @Override
    public Address getSendAddressByUserId(String id) {
        return this.lambdaQuery().eq(Address::getUserId, id).eq(Address::getSendStatus, 1).one();
    }

    @Override
    public Address getReceiveAddressByUserId(String id) {
        return this.lambdaQuery().eq(Address::getUserId, id).eq(Address::getReceiveStatus, 1).one();
    }
}
