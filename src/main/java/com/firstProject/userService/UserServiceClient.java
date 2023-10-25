package com.firstProject.userService;

import com.firstProject.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "UserServiceClient",
        url = "${UserServiceClient.url}"
)
public interface UserServiceClient {
    @GetMapping(value = "/user/getUserById/{userId}")
    ResponseEntity<User> getUserById(@PathVariable Long userId);
    @GetMapping(value = "/user/getUserByEmail/{email}")
    User getUserByEmail(@PathVariable String email);

}
