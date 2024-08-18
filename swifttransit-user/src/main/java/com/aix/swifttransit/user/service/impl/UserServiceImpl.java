package com.aix.swifttransit.user.service.impl;

import com.aix.swifttransit.user.dto.UserCredentialsDTO;
import com.aix.swifttransit.user.entity.User;
import com.aix.swifttransit.user.mapper.UserMapper;
import com.aix.swifttransit.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserCredentialsDTO getByUsername(String username) {
        var user = lambdaQuery().eq(User::getUsername, username)
                .select(User::getUsername, User::getPassword, User::getStatus, User::getDeleted)
                .one();
        if (ObjectUtils.isEmpty(user)) throw new RuntimeException("用户不存在");
        var userCredentialsDTO = new UserCredentialsDTO();
        BeanUtils.copyProperties(user, userCredentialsDTO);
        return userCredentialsDTO;
    }
}
