package com.task.workshop.service.impl;

import com.task.workshop.dto.TaskCreateRequest;
import com.task.workshop.dto.TaskDTO;
import com.task.workshop.entity.Employee;
import com.task.workshop.entity.Task;
import com.task.workshop.error.WorkshopException;
import com.task.workshop.mappers.TaskMapper;
import com.task.workshop.repository.EmployeeRepository;
import com.task.workshop.repository.TaskRepository;
import com.task.workshop.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final EmployeeRepository employeeRepository;

    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<TaskDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper.INSTANCE::dtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getById(Long id) {
        return taskRepository.findById(id)
                .map(TaskMapper.INSTANCE::dtoFromEntity)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO saveTaskWithAssignee(TaskCreateRequest task, Long employeeId) {
        Employee assignee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new WorkshopException("Employee with id " + employeeId + " does not exist"));

        task.setAssignee(assignee);
        Task entity = TaskMapper.INSTANCE.createRequestToEntity(task);
        Task saved = taskRepository.save(entity);
        return TaskMapper.INSTANCE.dtoFromEntity(saved);
    }

    @Override
    public TaskDTO update(TaskDTO task) {
        Task entity = TaskMapper.INSTANCE.dtoToEntity(task);
        Task updated = taskRepository.save(entity);
        return TaskMapper.INSTANCE.dtoFromEntity(updated);
    }
}