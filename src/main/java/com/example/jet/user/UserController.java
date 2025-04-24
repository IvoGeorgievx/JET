package com.example.jet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("sign-up")
    UserDTO createUser(@RequestBody SignUpRequest signUpRequest) {
        return this.userService.createUser(signUpRequest.getUsername(), signUpRequest.getPassword());
    }

    @GetMapping("all")
    List<UserDTO> getUsers() {
        return this.userService.getUsers();
    }
}
