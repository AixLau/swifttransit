package com.aix.swifttransit.auth.client;

import com.aix.swifttransit.auth.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 这个 Client 将用于从用户服务中获取用户信息。
 */
// @FeignClient(name = "swifttransit-user", path = "/user")
public interface UserClient {

    @GetMapping("/findByUsername")
    UserResponse findByUsername(@RequestParam("username") String username);
}
