package com.task.workshop.mappers;

import com.task.workshop.dto.TaskCreateRequest;
import com.task.workshop.dto.TaskDTO;
import com.task.workshop.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task dtoToEntity(TaskDTO dto);

    Task createRequestToEntity(TaskCreateRequest dto);

    TaskDTO dtoFromEntity(Task employee);

}
