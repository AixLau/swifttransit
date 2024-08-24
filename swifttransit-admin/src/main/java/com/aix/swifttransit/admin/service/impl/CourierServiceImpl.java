package com.aix.swifttransit.admin.service.impl;

import com.aix.swifttransit.admin.entity.Courier;
import com.aix.swifttransit.admin.mapper.CourierMapper;
import com.aix.swifttransit.admin.service.CourierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 快递员信息表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
@Service
public class CourierServiceImpl extends ServiceImpl<CourierMapper, Courier> implements CourierService {

    @Override
    public List<Courier> searchCouriers(String account, String name, String phone, Long organizationId) {
        return this.lambdaQuery().like(ObjectUtils.isNotEmpty(account), Courier::getAccount, account)
                .like(ObjectUtils.isNotEmpty(name), Courier::getName, name)
                .like(ObjectUtils.isNotEmpty(phone), Courier::getPhone, phone)
                .eq(ObjectUtils.isNotEmpty(organizationId), Courier::getOrganizationId, organizationId)
                .list();
    }
}
