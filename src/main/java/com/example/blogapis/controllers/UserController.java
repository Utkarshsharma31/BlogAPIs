package com.example.blogapis.controllers;

import com.example.blogapis.payloads.APIResponse;
import com.example.blogapis.payloads.UserDTO;
import com.example.blogapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //POST -create User

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
           UserDTO createdUser = this.userService.createUser(userDTO);
           return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    //PUT - update USer
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable("userId") Integer userId ){
        UserDTO updatedUser = this.userService.updateUser(userDTO, userId);
        return  ResponseEntity.ok(updatedUser);
    }
    //DELETE - delete user
    @DeleteMapping("/{userID}")
    public ResponseEntity<APIResponse> deletedUser(@PathVariable("userID") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<APIResponse>(new APIResponse("User Delete successfully",true),HttpStatus.OK);
    }
    //GET - ALL User
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> allUser(){
        this.userService.getAllUser();
        return ResponseEntity.ok(this.userService.getAllUser());
    }
    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> singleUser(@PathVariable("userID") Integer uid){
        UserDTO userByID = this.userService.getUserByID(uid);
        return ResponseEntity.ok(userByID);
    }
}
