package com.example.demo.exception;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ---------- 400 BAD REQUEST ---------- */

    @ExceptionHandler({
            ValidationException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /* ---------- 404 NOT FOUND ---------- */

    @ExceptionHandler({
            ResourceNotFoundException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /* ---------- HIBERNATE / JPA ERRORS ---------- */

    @ExceptionHandler({
            TransientPropertyValueException.class,
            PropertyValueException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<Map<String, Object>> handleHibernateErrors(Exception ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid request data. Please check referenced entities and required fields."
        );
    }

    /* ---------- FALLBACK (NO MORE RAW 500s) ---------- */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected server error occurred"
        );
    }

    /* ---------- RESPONSE BUILDER ---------- */

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
