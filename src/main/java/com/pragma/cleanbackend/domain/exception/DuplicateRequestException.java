package com.pragma.cleanbackend.domain.exception;


import com.pragma.cleanbackend.domain.Request;
import java.time.LocalDateTime;
import java.util.UUID;

public class DuplicateRequestException extends RuntimeException {
    private final UUID existingRequestId;
    private final String clientId;
    private final LocalDateTime timestamp;

    public DuplicateRequestException(String message, UUID existingRequestId, String clientId) {
        super(message);
        this.existingRequestId = existingRequestId;
        this.clientId = clientId;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getExistingRequestId() {
        return existingRequestId;
    }

    public String getClientId() {
        return clientId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLogMessage() {
        return String.format(
            "DuplicateRequestException: [existingRequestId=%s, clientId=%s, timestamp=%s] - %s",
            existingRequestId, clientId, timestamp, getMessage()
        );
    }

    public static DuplicateRequestException forRequest(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        String message = String.format(
            "Ya existe una solicitud con clientId '%s', descripción '%s' y detalles '%s'",
            request.getClientId(),
            request.getDescription(),
            request.getDetails()
        );
        return new DuplicateRequestException(
            message,
            request.getId(),
            request.getClientId()
        );
    }
}