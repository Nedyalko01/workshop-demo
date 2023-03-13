package com.task.workshop.dto;

import com.task.workshop.entity.Employee;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCreateRequest {

    private String title;

    private String description;

    private Employee assignee;

    private LocalDate dueDate;
}






