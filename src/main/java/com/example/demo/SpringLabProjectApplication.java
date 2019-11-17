package com.example.demo;

import com.example.demo.dao.TaskRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskPriority;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class SpringLabProjectApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringLabProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLabProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TaskRepository taskRepository, UserRepository userRepository) {
        return (args) -> {

            userRepository.save(new User(1L, "name", "surname", "email1", "112",
                    "password", "", UserRole.USER));
            userRepository.save(new User(2L, "name", "surname", "email2", "112",
                    "password", "", UserRole.USER));
            // fetch users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }
            log.info("");


            // save a few customers
            taskRepository.save(new Task(1L, "dfg", false, TaskPriority.MEDIUM,"", 1L));
            taskRepository.save(new Task(2L, "dfg", false, TaskPriority.MEDIUM,"", 2L));
            taskRepository.save(new Task(3L, "dfg", false, TaskPriority.MEDIUM,"", 2L));


            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Task task : taskRepository.findAll()) {
                log.info(task.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional task = taskRepository.findById(1L);
            log.info("Task is found with findById(1L):");
            log.info("--------------------------------");
            log.info(task.toString());
            log.info("");

//            // fetch an individual customer by ID
//            Optional task = taskRepository.findById(1L);
//            log.info("Task is found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(task.toString());
//            log.info("");


        };
    }

}
