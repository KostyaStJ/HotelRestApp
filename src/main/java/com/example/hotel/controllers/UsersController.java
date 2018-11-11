package com.example.hotel.controllers;

import com.example.hotel.entities.User;
import com.example.hotel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UsersController {
    private final UserService userService;

    @GetMapping("/add")
    public User addUser(@RequestParam String login, @RequestParam(required = false) String name, @RequestParam(required = false) String lastName) {

        return userService.createNewUser(login, name, lastName);

    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


}
