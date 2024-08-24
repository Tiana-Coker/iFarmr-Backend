package org.ifarmr.service.impl;

import org.ifarmr.entity.Task;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.ConflictException;
import org.ifarmr.exceptions.IFarmServiceException;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.NewTaskRequest;
import org.ifarmr.payload.response.TaskInfo;
import org.ifarmr.payload.response.TaskResponseDto;
import org.ifarmr.repository.TaskRepository;
import org.ifarmr.service.TaskService;
import org.ifarmr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Override
    public TaskResponseDto createTask(NewTaskRequest taskRequest, String currentUsername) {
        User user = userService.findByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException("User not found"));


        boolean taskExists = taskRepository.existsByTitleAndDueDateAndUser(taskRequest.getTitle(), taskRequest.getDueDate(), user);
        if (taskExists) {
            throw new ConflictException("Task with the same title and due date already exists.");
        }

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .location(taskRequest.getLocation())
                .category(taskRequest.getCategory())
                .dueDate(taskRequest.getDueDate())
                .description(taskRequest.getDescription())
                .user(user)
                .build();

        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new IFarmServiceException("An error occurred while saving the task", e);
        }


        return TaskResponseDto.builder()
                 .responseMessage("Task successfully created! ")
                 .taskInfo(TaskInfo.builder()
                         .title(task.getTitle())
                         .location(task.getLocation())
                         .category(task.getCategory())
                         .dueDate(task.getDueDate())
                         .description(task.getDescription())
                         .build())
                 .build();

    }
}
