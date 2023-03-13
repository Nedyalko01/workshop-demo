package com.task.workshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDTO {

    @NotNull
    private Long id;
    private String fullName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private BigDecimal monthlySalary;
}