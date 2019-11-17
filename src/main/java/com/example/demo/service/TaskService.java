package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    void deleteTask(Long id);

    List<Task> getAllTasks();

    List<Task> getAllUserTasks(Long id);

    Task setState(Long id, boolean state);

}
