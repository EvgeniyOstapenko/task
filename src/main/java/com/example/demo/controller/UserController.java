package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User singUp(User user) {
        return userService.toRegister(user);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/singIn")
    public User singIn(User user) {
        return userService.toEnter(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public List<Task> findAllUserTask(@PathVariable Long userId) {
        return taskService.getAllUserTasks(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}")
    public User getSubscription(@PathVariable Long userId) {
        return userService.subscribe(userId);
    }


}
