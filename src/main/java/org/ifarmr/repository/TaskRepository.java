package org.ifarmr.repository;

import org.ifarmr.entity.Task;
import org.ifarmr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitleAndDueDateAndUser(String title, LocalDate dueDate, User user);
}
