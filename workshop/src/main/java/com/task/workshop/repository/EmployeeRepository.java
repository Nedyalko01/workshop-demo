package com.task.workshop.repository;

import com.task.workshop.dto.EmployeeTaskCount;
import com.task.workshop.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.task.workshop.dto.EmployeeTaskCount(t.assignee, COUNT(t)) " +
            "FROM Task t " +
            "WHERE t.dueDate BETWEEN :startDate AND :endDate " +
            "GROUP BY t.assignee " +
            "ORDER BY COUNT(t) DESC")
    List<EmployeeTaskCount> findTop5ByTaskCountForPreviousMonth(@Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate);
}

