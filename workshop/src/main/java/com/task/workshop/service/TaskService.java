package com.task.workshop.service;

import com.task.workshop.dto.TaskCreateRequest;
import com.task.workshop.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO saveTaskWithAssignee(TaskCreateRequest task, Long employeeId);

    TaskDTO update(TaskDTO task);

    TaskDTO getById(Long id);

    List<TaskDTO> getAll();

    void deleteById(Long id);

}
