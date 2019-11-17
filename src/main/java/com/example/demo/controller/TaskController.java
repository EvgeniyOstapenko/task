package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Task> getAll(){
        return taskService.getAllTasks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Task createTask(Task task){
        return taskService.createTask(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/completed")
    public Task markTaskComplete(@PathVariable Long id) {
        return taskService.setState(id, true);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/not_completed")
    public Task markTaskNotComplete(@PathVariable Long id) {
        return taskService.setState(id, false);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sort")
    public List<Task> getSortedTasks() {
        return taskService.getSortedTasks();
    }

    @PostMapping("/{taskId}/upload")
    public boolean uploadFile(@PathVariable Long taskId, @RequestParam("file") MultipartFile file) {
        return taskService.upload(file, taskId);
    }

}
