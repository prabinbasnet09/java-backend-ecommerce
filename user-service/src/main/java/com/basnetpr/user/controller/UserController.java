package com.basnetpr.user.controller;

import com.basnetpr.user.entity.User;
import com.basnetpr.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        log.info("Inside getAllUsers of UserController");
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public User createNewUser(@RequestBody User user){
        log.info("Inside createNewUser of UserController");
        return userService.createNewUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        log.info("Inside loginUser of UserController");
        return userService.loginUser(user);
    }
}
