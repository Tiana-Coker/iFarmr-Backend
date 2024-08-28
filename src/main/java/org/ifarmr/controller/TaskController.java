package org.ifarmr.controller;

import jakarta.validation.Valid;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.NewTaskRequest;
import org.ifarmr.payload.response.TaskResponseDto;
import org.ifarmr.payload.response.UpcomingTaskResponse;
import org.ifarmr.service.TaskService;
import org.ifarmr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;



    @PostMapping("/create")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody NewTaskRequest taskRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();


        TaskResponseDto createdTask = taskService.createTask(taskRequest, currentUsername);

        return ResponseEntity.status(201).body(createdTask);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<UpcomingTaskResponse>> getUpcomingTasks(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        List<UpcomingTaskResponse> upcomingTasks = taskService.getUpcomingTasks(currentUsername);
        return ResponseEntity.ok(upcomingTasks);

    }
}