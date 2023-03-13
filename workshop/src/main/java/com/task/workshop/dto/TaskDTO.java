package com.task.workshop.dto;

import com.task.workshop.entity.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {

    @NotNull
    private Long id;

    private String title;

    private String description;

    private Employee assignee;

    private LocalDate dueDate;
}






