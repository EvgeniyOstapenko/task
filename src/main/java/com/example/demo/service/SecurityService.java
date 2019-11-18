package com.example.demo.service;

import com.example.demo.entity.UserRole;
import com.example.demo.exception.UnauthorizedAccessException;
import org.springframework.stereotype.Service;

@Service
class SecurityService {

    void isValidUserRole(UserRole userRole) {
        if (!UserRole.ADMIN.equals(userRole))
            throw new UnauthorizedAccessException("This is ADMIN service!");
    }
}
