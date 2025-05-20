package com.example.jet.user;

import com.example.jet.config.AuthenticatedUser;
import com.example.jet.user.dto.SignInDTO;
import com.example.jet.user.dto.SignInResponseDTO;
import com.example.jet.user.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    UserDTO createUser(@RequestBody SignUpDTO signUpRequest) {
        return this.userService.createUser(signUpRequest.getUsername(), signUpRequest.getPassword());
    }

    @PostMapping("sign-in")
    SignInResponseDTO login(@RequestBody SignInDTO signInDTO) {
        return this.userService.login(signInDTO);
    }

    @GetMapping("all")
    List<UserDTO> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("info")
    UserDTO getCurrentUser(@AuthenticationPrincipal AuthenticatedUser user) {
        return this.userService.getCurrentUser(user.getUserId());
    }
}
