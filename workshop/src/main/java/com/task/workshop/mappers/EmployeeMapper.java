package com.task.workshop.mappers;

import com.task.workshop.dto.EmployeeCreateRequest;
import com.task.workshop.dto.EmployeeDTO;
import com.task.workshop.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee dtoToEntity(EmployeeDTO dto);

    Employee createRequestToEntity(EmployeeCreateRequest dto);

    EmployeeDTO dtoFromEntity(Employee employee);

}
