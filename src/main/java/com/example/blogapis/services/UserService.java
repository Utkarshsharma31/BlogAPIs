package com.example.blogapis.services;

import com.example.blogapis.entities.Users;
import com.example.blogapis.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user,Integer userID);

    UserDTO getUserByID(Integer userID);

    List<UserDTO> getAllUser();

    void deleteUser(Integer userId);



}
