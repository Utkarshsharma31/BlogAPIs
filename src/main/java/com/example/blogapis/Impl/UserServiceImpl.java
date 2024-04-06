package com.example.blogapis.Impl;

import com.example.blogapis.entities.Users;
import com.example.blogapis.exceptions.ResourceNotFoundException;
import com.example.blogapis.payloads.UserDTO;
import com.example.blogapis.repository.UserRepo;
import com.example.blogapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Users user = this.dtoToUser(userDTO);
       Users savedUser =  this.userRepo.save(user);
        return userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userID) {

        Users oldInfo = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","id",userID));
        oldInfo.setName(userDTO.getName());
        oldInfo.setPassword(userDTO.getPassword());
        oldInfo.setEmail(userDTO.getEmail());
        oldInfo.setAbout(userDTO.getAbout());

        Users updatedUser = this.userRepo.save(oldInfo);

        UserDTO userDTO1 = this.userToDTO(updatedUser);

        return userDTO1;



    }

    @Override
    public UserDTO getUserByID(Integer userID) {
        Users userInfo = this.userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("user","id",userID));

        return this.userToDTO(userInfo);

    }

    @Override
    public List<UserDTO> getAllUser() {
        List<Users> allUsers = this.userRepo.findAll();

       List<UserDTO> allUsersDTO;
        allUsersDTO = allUsers.stream().map(user->this.userToDTO(user)).collect(Collectors.toList());
        return allUsersDTO;
    }

    @Override
    public void deleteUser(Integer userId) {

        Users users = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        this.userRepo.delete(users);
    }

    public Users dtoToUser(UserDTO userDTO){
            Users user = new Users();
            user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO userToDTO(Users users){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setName(users.getName());
        userDTO.setEmail(users.getEmail());
        userDTO.setAbout(users.getAbout());
        userDTO.setPassword(users.getPassword());
        return userDTO;
    }

}
