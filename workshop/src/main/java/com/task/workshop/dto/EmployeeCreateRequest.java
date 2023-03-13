package com.task.workshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeCreateRequest {
    private String fullName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private BigDecimal monthlySalary;
}