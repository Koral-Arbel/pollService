package com.firstProject.userService;

import com.firstProject.model.UserAnswer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "UserService",
        url = "http://localhost:8090"
)
public interface UserService {
    @GetMapping(value = "/user/{userId}")
    UserAnswer getUserAnswerById(@PathVariable Long userId);

    @GetMapping(value = "/user")
    UserAnswer getUserAnswerByEmail(@RequestParam String email);
    @GetMapping(value = "/registered")
    boolean getRegistered();
}
