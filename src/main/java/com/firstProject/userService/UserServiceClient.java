package com.firstProject.userService;

import com.firstProject.model.User;
import com.firstProject.model.UserAnswer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "UserService",
        url = "http://localhost:8082"
)
public interface UserServiceClient {

    @GetMapping(value = "/user/{userId}")
    UserAnswer getUserAnswerById(@RequestParam Long userId);

    @GetMapping(value = "/user")
    User getUserAnswerByEmail(@RequestParam String email);
    @GetMapping(value = "/registered")
    boolean getRegistered();

}

