package com.task.workshop.dto;

import com.task.workshop.entity.Employee;

public class EmployeeTaskCount implements Comparable<EmployeeTaskCount> {

    private Employee employee;
    private Long taskCount;

    public EmployeeTaskCount(Employee employee, Long taskCount) {
        this.employee = employee;
        this.taskCount = taskCount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Long getTaskCount() {
        return taskCount;
    }

    @Override
    public int compareTo(EmployeeTaskCount other) {
        return Long.compare(taskCount, other.taskCount);
    }
}
