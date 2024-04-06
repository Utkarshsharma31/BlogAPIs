package com.example.blogapis.repository;

import com.example.blogapis.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<Users,Integer> {

}
