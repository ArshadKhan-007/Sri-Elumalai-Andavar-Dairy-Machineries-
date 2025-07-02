package dev.misfit.backend.controllers;

import dev.misfit.backend.Services.UserService;
import dev.misfit.backend.entity.UserEntity;
import dev.misfit.backend.io.UserRequest;
import dev.misfit.backend.io.UserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserEntity user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest user){
        return userService.login(user);
    }
}
