package com.energyplatform.site_account_management.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Custom application/business exceptions.
     */
    @ExceptionHandler(SiteException.class)
    public ResponseEntity<String> handleSiteException(
            SiteException exception) {

        log.error("Site exception: {}", exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ShiftException.class)
    public ResponseEntity<String> handleShiftException(
            ShiftException exception) {

        log.error("Shift exception: {}", exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }


    /**
     * Handles invalid HTTP request bodies.
     *
     * Example:
     * - Invalid JSON
     * - JSON type mismatch
     * - Cannot deserialize JSON into an object
     * - Missing/invalid request body
     */
    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<String> handleWebInputException(
            ServerWebInputException exception) {

        log.error("Failed to read HTTP request", exception);

        Throwable cause = exception.getCause();

        String message;

        if (cause != null && cause.getMessage() != null) {
            message = "Invalid request body: " + cause.getMessage();
        } else {
            message = "Invalid request body: " + exception.getMessage();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }


    /**
     * Handles validation errors when using @Valid.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleValidationException(
            WebExchangeBindException exception) {

        log.error("Validation failed", exception);

        String message = exception.getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage()
                )
                .findFirst()
                .orElse("Validation failed");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }


    /**
     * Handles ResponseStatusException.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(
            ResponseStatusException exception) {

        log.error("HTTP exception: {}", exception.getMessage());

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(exception.getReason());
    }


    /**
     * Fallback for unexpected exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(
            Exception exception) {

        log.error("Unexpected exception occurred", exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }
}