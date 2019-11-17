package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Task;
import com.example.demo.exception.SubscriptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String SECRET = DigestUtils.md5DigestAsHex(("secret".getBytes()));
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
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
    public Task setState(Long taskId, boolean state) {
        Task currentTask = taskRepository.findById(taskId).get();
        currentTask.setDone(state);
        return taskRepository.save(currentTask);
    }

    @Override
    public List<Task> getSortedTasks() {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        tasks.sort(Comparator.comparing(Task::getTaskPriority));
        return tasks;
    }

    @Override
    public List<Task> getAllUserTasks(Long userId) {
        return taskRepository.findAllTasksByUserId(userId);
    }

    @Override
    public boolean upload(MultipartFile file, Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        checkSubscription(task.getUserId());
        task.setFile(file.getName());
        return true;
    }

    private void checkSubscription(Long userId) {
         if(!userRepository.findById(userId).get().getSubscription().equals(SECRET))
             throw new SubscriptionException("The uploading file option is only for subscribers!");
    }
}
