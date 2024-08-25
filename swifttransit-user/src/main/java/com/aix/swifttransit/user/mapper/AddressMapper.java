package com.aix.swifttransit.user.mapper;

import com.aix.swifttransit.user.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储用户地址信息的表，包括发货和收货地址 Mapper 接口
 * </p>
 *
 * @author aix
 * @since 2024-08-25
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}
