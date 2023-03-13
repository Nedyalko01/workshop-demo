package com.task.workshop.controller;

import com.task.workshop.dto.TaskCreateRequest;
import com.task.workshop.dto.TaskDTO;
import com.task.workshop.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @NotNull TaskCreateRequest task, @RequestParam Long assigneeId) {
        TaskDTO newTask = taskService.saveTaskWithAssignee(task, assigneeId);
        return ResponseEntity.created(URI.create("/api/tasks/" + newTask.getId())).body(newTask);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody @NotNull @Valid TaskDTO task) {
        TaskDTO newTask = taskService.update(task);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO task = taskService.getById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAll();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}