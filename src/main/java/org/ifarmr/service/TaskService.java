package org.ifarmr.service;

import org.ifarmr.entity.Task;
import org.ifarmr.entity.User;
import org.ifarmr.payload.request.NewTaskRequest;
import org.ifarmr.payload.response.TaskResponseDto;

public interface TaskService {
    TaskResponseDto createTask(NewTaskRequest taskRequest, User user);
}
