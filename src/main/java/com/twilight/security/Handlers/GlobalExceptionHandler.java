package com.twilight.security.Handlers;
import com.twilight.exceptions.GeocodingError;
import com.twilight.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.twilight.dataTransferObjects.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );
        log.info("\n{}\n", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSqlExceptions(SQLException ex){
        log.info("\nMessage:{} ,SQL State: {}\n", ex.getMessage(),ex.getSQLState());
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Operation Not Permitted");
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestExceptions(BadRequestException ex){
        log.info("\n{}\n", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(ex.getError()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundExceptions(NotFoundException ex){
        log.info("\n{}\n", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(ex.getError()));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedExceptions(UnAuthorizedException ex){
        log.warn("\n{}\n", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Message(ex.getError()));
    }

    @ExceptionHandler(GeocodingError.class)
    public ResponseEntity<?> handleGeocodingExceptions(GeocodingError ex){
        log.info("\n{}\n", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(ex.getError()));
    }
    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<?> handleServerExceptions(SomethingWentWrongException ex){
        log.error("\n{}\n", ex.getMessage());
        return ResponseEntity.internalServerError().body(new Message(ex.getError()));
    }
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<?> handleFileTypeExceptions(GeocodingError ex){   
        log.info("\n{}\n", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new Message(ex.getError()));
    }

    @ExceptionHandler( ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex){
        log.info("\n{}\n", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Invalid request"));
    }
}
