package com.bali.personal_trainer.components.Security.Handlers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Invalid JSON Request Error,", "message", e.getMessage(), "Handler","handleHttpMessageNotReadable"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        // Extracting the root cause of the DataIntegrityViolationException
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException sqlException) {
            String sqlErrorMessage = String.format("SQL Error: Code: %d, Message: %s",
                    sqlException.getErrorCode(),
                    sqlException.getMessage());

            return ResponseEntity.status(409)
                    .body(Map.of("error", "Database error", "message", sqlErrorMessage, "Handler","handleDataIntegrityViolationException SQL Exception"));
        }

        // Fallback if root cause is not an instance of SQLException
        return ResponseEntity.status(500)
                .body(Map.of("error", "Database error", "message", e.getMessage(), "Handler","handleDataIntegrityViolationException"));
    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<?> UnexpectedRollbackExceptionHandler(UnexpectedRollbackException e)
    {
        return ResponseEntity.status(500).body(
                Map.of("error","Error while processing request", "message", e.getMessage(),"Handler","UnexpectedRollbackExceptionHandler"));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> UnexpectedSQLExceptionHandler(SQLException e)
    {
        return ResponseEntity.status(500).body(
                Map.of("error","Error while processing request for SQL Error", "message", e.getMessage(),"Handler","UnexpectedSQLExceptionHandler"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationExceptionHandler(ConstraintViolationException e)
    {
        return ResponseEntity.status(500).body(
                Map.of("error","Error while processing request", "message", e.getMessage(),"Handler","ConstraintViolationExceptionHandler"));
    }

    // Catch-all for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of("error", "Unexpected error...", "message", e.getMessage(), "Handler","handleGeneralException"));
    }

    // Catch-all for other throwables
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleThrowableException(Throwable e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of("error", "Unexpected throwable", "message", e.getMessage(), "Handler","handleThrowableException"));
    }



}
