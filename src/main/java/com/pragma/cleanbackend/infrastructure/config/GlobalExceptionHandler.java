package com.pragma.cleanbackend.infrastructure.config;

import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequest(InvalidRequestException e) {
        logger.error("Solicitud invalida detectada - Mensaje: {} - Campo: {}", 
                     e.getMessage(), e.getClass().getSimpleName());
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Solicitud invalida",
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateRequest(DuplicateRequestException e) {
        logger.error("Solicitud duplicada detectada - Cliente: {} - ID existente: {}", 
                     e.getClientId(), e.getExistingRequestId());
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                "Solicitud duplicada",
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        logger.error("Errores de validacion en argumentos del request: {} errores encontrados", 
                     e.getBindingResult().getFieldErrors().size());
        
        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
            logger.warn("Error en campo '{}': {}", fieldName, errorMessage);
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validacion de entrada fallida");
        response.put("fieldErrors", fieldErrors);
        response.put("timestamp", LocalDateTime.now().toString());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException e) {
        logger.error("Argumento ilegal proporcionado: {}", e.getMessage());
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Argumento invalido",
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception e) {
        logger.error("Excepcion no manejada - Tipo: {} - Mensaje: {}", 
                     e.getClass().getName(), e.getMessage(), e);
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                "Ocurrio un error inesperado. Por favor contacte al administrador.",
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public record ExceptionResponse(int status, String error, String message, String timestamp) {}
}