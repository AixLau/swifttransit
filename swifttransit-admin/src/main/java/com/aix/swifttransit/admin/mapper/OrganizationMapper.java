package com.aix.swifttransit.admin.mapper;


import com.aix.swifttransit.admin.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 机构信息表，存储机构的基本信息 Mapper 接口
 * </p>
 *
 * @author aix
 * @since 2024-08-20
 */
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

}
