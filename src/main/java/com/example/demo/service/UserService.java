package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User toRegister(User user);

    User toEnter(User user);

    User subscribe(Long userId);

    User getById(Long userId);

    List<User> getAllUsers();

//    void isAdminAuthority(User user);
}
