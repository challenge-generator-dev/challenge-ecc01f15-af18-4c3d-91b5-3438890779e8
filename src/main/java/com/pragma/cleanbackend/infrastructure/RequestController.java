package com.pragma.cleanbackend.infrastructure;

import com.pragma.cleanbackend.Application.RequestDto;
import com.pragma.cleanbackend.application.RequestService;
import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody RequestDto requestDto) {
        logger.info("Recibida solicitud de cliente: {} - Descripcion: {}", 
                    requestDto.clientId(), requestDto.description());
        try {
            Request createdRequest = requestService.create(requestDto);
            logger.info("Solicitud creada exitosamente con ID: {}", createdRequest.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
        } catch (InvalidRequestException e) {
            logger.warn("Error de validacion al crear solicitud: {}", e.getMessage());
            throw e;
        } catch (DuplicateRequestException e) {
            logger.warn("Solicitud duplicada detectada para cliente: {}", e.getClientId());
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        logger.info("Obteniendo todas las solicitudes");
        List<Request> requests = requestService.findAll();
        logger.info("Se encontraron {} solicitudes", requests.size());
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable UUID id) {
        logger.info("Buscando solicitud con ID: {}", id);
        return requestService.findById(id)
                .map(request -> {
                    logger.info("Solicitud encontrada: {}", request.getId());
                    return ResponseEntity.ok(request);
                })
                .orElseGet(() -> {
                    logger.warn("Solicitud no encontrada con ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable UUID id) {
        logger.info("Eliminando solicitud con ID: {}", id);
        if (requestService.deleteById(id)) {
            logger.info("Solicitud eliminada exitosamente: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("No se pudo eliminar, solicitud no encontrada: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidRequestException e) {
        logger.error("Excepcion de solicitud invalida: {}", e.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRequest(DuplicateRequestException e) {
        logger.error("Excepcion de solicitud duplicada: {}", e.getLogMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    public record ErrorResponse(int status, String message, long timestamp) {}
}