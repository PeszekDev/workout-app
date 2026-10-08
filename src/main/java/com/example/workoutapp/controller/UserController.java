package com.example.workoutapp.controller;


import com.example.workoutapp.dto.UserDto;
import com.example.workoutapp.model.User;
import com.example.workoutapp.repository.UserRepository;
import com.example.workoutapp.service.UserService;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    public UserDto getUserById(@PathVariable("userId") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping(path = "{userId}")
    public void changeUser(@PathVariable("userId") Long userId ,@RequestParam(required = false) String name , @RequestParam(required = false) String email) {
        userService.updateUser(userId ,name ,email);
    };

}
