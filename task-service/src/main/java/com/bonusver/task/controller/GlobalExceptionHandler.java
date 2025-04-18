package com.bonusver.task.controller;

import com.bonusver.task.controller.exceptions.NotAuthorExecutorException;
import com.bonusver.task.controller.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorExecutorException.class)
    public ResponseEntity<String> handleNotAuthorExecutor(NotAuthorExecutorException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
