package com.task.workshop.controller;

import com.task.workshop.dto.EmployeeCreateRequest;
import com.task.workshop.dto.EmployeeDTO;
import com.task.workshop.dto.EmployeeTaskCount;
import com.task.workshop.dto.TaskDTO;
import com.task.workshop.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @NotNull EmployeeCreateRequest employee) {
        EmployeeDTO newEmployee = employeeService.save(employee);
        return ResponseEntity.created(URI.create("/api/employees/" + newEmployee.getId())).body(newEmployee);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @NotNull @Valid EmployeeDTO employee) {
        EmployeeDTO newEmployee = employeeService.update(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByEmployeeId(@PathVariable Long id) {
        List<TaskDTO> tasks = employeeService.getTasksByEmployeeId(id);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top-employees")
    public ResponseEntity<List<EmployeeTaskCount>> getTopEmployeesByTaskCountForPreviousMonth() {
        return ResponseEntity.ok(employeeService.getTopEmployeesByTaskCountForPreviousMonth());
    }
}
