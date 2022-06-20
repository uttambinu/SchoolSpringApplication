package com.school.schoolDemo.controller;

import com.school.schoolDemo.entity.User;
import com.school.schoolDemo.payload.UserDTO;
import com.school.schoolDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }
}
