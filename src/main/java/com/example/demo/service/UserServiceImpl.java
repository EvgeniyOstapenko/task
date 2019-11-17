package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.WrongPasswordException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final String KEY = "secret";

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User toRegister(User currentUser) {
        if (userRepository.getByEmail(currentUser.getEmail()) != null)
            throw new EmailAlreadyExistException("This email is already in use!");
        return userRepository.save(currentUser);
    }

    @Override
    public User toEnter(User currentUser) {
        User userFromDB = userRepository.findByEmail(currentUser.getEmail());
        return authorization(currentUser, userFromDB);
    }

    private User authorization(User currentUser, User userFromDB) {
        if (userFromDB == null)
            throw new UserNotFoundException("Unregistered userFromDB!");
        else if (!currentUser.getPassword().equals(userFromDB.getPassword()))
            throw new WrongPasswordException("Wrong password!");
        return userFromDB;
    }

    @Override
    public User subscribe(Long userId) {
        User userFromDb = userRepository.findById(userId).get();
        userFromDb.setSubscription(DigestUtils.md5Hex(KEY));
        return userFromDb;
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

//    @Override
//    public void isAdminAuthority(User user) {
//        SecurityService service = getSecurityService();
//        try {
//            service.isValidUserRole(user.getUserRole());
//            System.out.println("Welcome admin.");
//        } catch (UnauthorizedAccessAttemptException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SecurityService getSecurityService() {
//        ClassPathXmlApplicationContext xmlContext =
//                new ClassPathXmlApplicationContext("spring-config.xml");
//        xmlContext.refresh();
//        return xmlContext.getBean(SecurityService.class);
//    }
}
