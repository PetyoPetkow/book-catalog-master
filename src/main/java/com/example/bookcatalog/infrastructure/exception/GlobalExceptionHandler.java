package com.example.bookcatalog.infrastructure.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String stamp = "timeStamp";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(ConstraintViolationException e){
        Map<String, String> errorMap = new HashMap<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()
        ) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        errorMap.put(stamp, formatter.format(ZonedDateTime.now()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException e){
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        errorMap.put(stamp, formatter.format(ZonedDateTime.now()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler({NotFoundException.class, SearchParamParseException.class,
            AlreadyExistsException.class, IncorrectPasswordException.class})
    public ResponseEntity<Map<String, String>> handleRuntimeExceptions(RuntimeException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", e.getMessage());
        errorMap.put(stamp, formatter.format(ZonedDateTime.now()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<Map<String, String>> handleDateTimeParseExceptions(RuntimeException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", e.getMessage());
        errorMap.put(stamp, formatter.format(ZonedDateTime.now()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
}
