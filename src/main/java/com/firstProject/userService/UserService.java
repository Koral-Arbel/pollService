package com.firstProject.userService;

import org.h2.engine.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "UserService",
        url = "${externalApi.userService.url}"
)
public interface UserService {
    @GetMapping(value = "/user/{userId}")
    User getUserById(@PathVariable Long userId);

    @GetMapping(value = "/user")
    User getUserByEmail(@RequestParam String email);
    }
