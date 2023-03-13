package com.task.workshop.service;

import com.task.workshop.dto.EmployeeCreateRequest;
import com.task.workshop.dto.EmployeeDTO;
import com.task.workshop.dto.EmployeeTaskCount;
import com.task.workshop.dto.TaskDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAll();

    EmployeeDTO getById(Long id);

    EmployeeDTO save(EmployeeCreateRequest employee);

    EmployeeDTO update(EmployeeDTO employee);

    void deleteById(Long id);

    List<TaskDTO> getTasksByEmployeeId(Long employeeId);

    List<EmployeeTaskCount> getTopEmployeesByTaskCountForPreviousMonth();
}
