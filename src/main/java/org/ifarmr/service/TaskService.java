package org.ifarmr.service;

import org.ifarmr.entity.Task;
import org.ifarmr.entity.User;
import org.ifarmr.payload.request.NewTaskRequest;
import org.ifarmr.payload.response.TaskResponseDto;
import org.ifarmr.payload.response.UpcomingTaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(NewTaskRequest taskRequest, String currentUsername);
    List<UpcomingTaskResponse> getUpcomingTasks(String username);
}
