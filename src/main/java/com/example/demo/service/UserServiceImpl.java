package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.WrongPasswordException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String SECRET = DigestUtils.md5Hex("secret");
    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Override
    public User toRegister(User currentUser) {
        if (userRepository.getByEmail(currentUser.getEmail()) != null)
            throw new EmailExistException("This email is already in use!");
        return userRepository.save(currentUser);
    }

    @Override
    public User toEnter(User currentUser) {
        User userFromDB = userRepository.findByEmail(currentUser.getEmail());
        return authorization(currentUser, userFromDB);
    }

    private User authorization(User currentUser, User userFromDB) {
        if (userFromDB == null)
            throw new UserNotFoundException("Unregistered user!");
        else if (!currentUser.getPassword().equals(userFromDB.getPassword()))
            throw new WrongPasswordException("Wrong password!");
        return userFromDB;
    }

    @Override
    public User subscribe(Long userId) {
        User userFromDb = userRepository.findById(userId).get();
        userFromDb.setSubscription(SECRET);
        return userRepository.save(userFromDb);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void isAdminAuthority(User user) {
        securityService.isValidUserRole(user.getUserRole());
    }
}
