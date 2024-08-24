package org.ifarmr.controller;

import jakarta.validation.Valid;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.NewTaskRequest;
import org.ifarmr.payload.response.TaskResponseDto;
import org.ifarmr.service.TaskService;
import org.ifarmr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody NewTaskRequest taskRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userService.findByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException("User not found"));

        TaskResponseDto createdTask = taskService.createTask(taskRequest, user);

        return ResponseEntity.status(201).body(createdTask);
    }
}
