package com.task.workshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WorkshopExceptionHandler {

    @ExceptionHandler(WorkshopException.class)
    public ResponseEntity<WorkshopError> handleWorkshopException(WorkshopException ex) {
        WorkshopError error = new WorkshopError(ex.getMessage(), null);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WorkshopError> handleException(Exception ex) {
        WorkshopError error = new WorkshopError(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}