package com.narin.api.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 user not found
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleUserNotFound(UserNotFoundException e) {
        return Map.of("error", e.getMessage());
    }

    // 401 invalid password
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String,String> handleInvalidPassword(InvalidPasswordException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,String> handleDuplicate(DuplicateUserException e) {
        return Map.of("error", e.getMessage());
    }

    // 400 validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidation(MethodArgumentNotValidException e) {

        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));

        return errors;
    }

    // fallback 500
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> handleRuntime(RuntimeException e) {
        return Map.of("error", e.getMessage());
    }
}
