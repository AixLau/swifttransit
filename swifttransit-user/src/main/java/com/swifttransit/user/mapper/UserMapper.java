package com.swifttransit.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swifttransit.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author aix
 * @since 2024-08-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
