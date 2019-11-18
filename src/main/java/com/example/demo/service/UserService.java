package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;

import java.util.List;

public interface UserService {

    User toRegister(User user);

    User toEnter(User user);

    User subscribe(Long userId);

    User getById(Long userId);

    List<User> getAllUsers();

    User changeRole(Long adminId, Long userId, UserRole userRole);

}
