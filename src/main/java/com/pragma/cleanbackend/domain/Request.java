package com.pragma.cleanbackend.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Request {
    private UUID id;

    @NotNull(message = "El clientId no puede ser nulo")
    @Size(min = 3, max = 50, message = "El clientId debe tener entre 3 y 50 caracteres")
    private String clientId;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 5, max = 200, message = "La descripción debe tener entre 5 y 200 caracteres")
    private String description;

    @NotNull(message = "Los detalles no pueden ser nulos")
    @Size(min = 5, max = 500, message = "Los detalles deben tener entre 5 y 500 caracteres")
    private String details;

    private LocalDateTime createdAt;
    private RequestStatus status;

    public Request() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.status = RequestStatus.PENDING;
    }

    public Request(String clientId, String description, String details) {
        this();
        this.clientId = clientId;
        this.description = description;
        this.details = details;
    }

    public enum RequestStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        REJECTED
    }

    public void validate() {
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("El clientId no puede estar vacío");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (details == null || details.trim().isEmpty()) {
            throw new IllegalArgumentException("Los detalles no pueden estar vacíos");
        }
    }

    public boolean isDuplicateOf(Request other) {
        if (other == null) {
            return false;
        }
        return this.clientId.equals(other.clientId) &&
               this.description.equals(other.description) &&
               this.details.equals(other.details);
    }
}