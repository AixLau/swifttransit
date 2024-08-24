package com.aix.swifttransit.admin.service;

import com.aix.swifttransit.admin.entity.Courier;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 快递员信息表 服务类
 * </p>
 *
 * @author aix
 * @since 2024-08-24
 */
public interface CourierService extends IService<Courier> {

    /**
     * 根据搜索条件获取快递员列表
     *
     * @param account        快递员账号
     * @param name           快递员姓名
     * @param phone          快递员手机号
     * @param organizationId 快递员所属机构id
     * @return 快递员列表
     */
    List<Courier> searchCouriers(String account, String name, String phone, Long organizationId);
}
