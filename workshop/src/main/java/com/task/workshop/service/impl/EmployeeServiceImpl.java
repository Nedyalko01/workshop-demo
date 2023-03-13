package com.task.workshop.service.impl;

import com.task.workshop.dto.EmployeeCreateRequest;
import com.task.workshop.dto.EmployeeDTO;
import com.task.workshop.dto.EmployeeTaskCount;
import com.task.workshop.dto.TaskDTO;
import com.task.workshop.entity.Employee;
import com.task.workshop.mappers.EmployeeMapper;
import com.task.workshop.mappers.TaskMapper;
import com.task.workshop.repository.EmployeeRepository;
import com.task.workshop.repository.TaskRepository;
import com.task.workshop.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper.INSTANCE::dtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper.INSTANCE::dtoFromEntity)
                .orElse(null);
    }

    @Override
    public EmployeeDTO save(EmployeeCreateRequest employee) {
        Employee entity = EmployeeMapper.INSTANCE.createRequestToEntity(employee);
        Employee saved = employeeRepository.save(entity);
        return EmployeeMapper.INSTANCE.dtoFromEntity(saved);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employee) {
        Employee entity = EmployeeMapper.INSTANCE.dtoToEntity(employee);
        Employee updated = employeeRepository.save(entity);
        return EmployeeMapper.INSTANCE.dtoFromEntity(updated);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<TaskDTO> getTasksByEmployeeId(Long employeeId) {
        return taskRepository.findByAssigneeId(employeeId)
                .stream()
                .map(TaskMapper.INSTANCE::dtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeTaskCount> getTopEmployeesByTaskCountForPreviousMonth() {
        LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        return employeeRepository.findTop5ByTaskCountForPreviousMonth(startDate, endDate);
    }
}