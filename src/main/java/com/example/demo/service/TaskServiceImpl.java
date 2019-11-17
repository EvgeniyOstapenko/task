package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        Task createdTask = taskRepository.save(task);
        return createdTask;
    }

    @Override
    public void deleteTask(Long id) throws RuntimeException {
        try {
            taskRepository.deleteById(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); //"No task with such ID found!"
        }
        System.out.println("Task : " + id + " has been successfully deleted!");
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task setState(Long id, boolean state) {

        Task currentTask = taskRepository.findById(id).get();
        currentTask.setDone(state);
        return taskRepository.save(currentTask);
    }

    @Override
    public List<Task> getAllUserTasks(Long id) {
        return (List<Task>) taskRepository.findAllTasksByUserId(id);
    }
}
