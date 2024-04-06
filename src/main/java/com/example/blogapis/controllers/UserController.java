package com.example.blogapis.controllers;

import com.example.blogapis.payloads.UserDTO;
import com.example.blogapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //POST -create User

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
           UserDTO createdUser = this.userService.createUser(userDTO);
           return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    //PUT - update USer

    //DELETE - delete user

    //GET - ALL User
}
