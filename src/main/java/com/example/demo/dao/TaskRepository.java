package com.example.demo.dao;

import com.example.demo.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllTasksByUserId(Long id);

}
