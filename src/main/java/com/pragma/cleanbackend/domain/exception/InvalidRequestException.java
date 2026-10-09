package com.pragma.cleanbackend.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class InvalidRequestException extends RuntimeException {
    private final String field;
    private final Object rejectedValue;
    private final String reason;
    private final LocalDateTime timestamp;
    private final UUID requestId;

    public InvalidRequestException(String message, String field, Object rejectedValue, String reason) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
        this.requestId = UUID.randomUUID();
    }

    public InvalidRequestException(String message, String field, Object rejectedValue) {
        this(message, field, rejectedValue, "Valor inválido para el campo especificado");
    }

    public InvalidRequestException(String message) {
        this(message, null, null, "Solicitud no válida");
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvalidRequestException[")
          .append("id=").append(requestId)
          .append(", timestamp=").append(timestamp)
          .append(", message=").append(getMessage());
        if (field != null) {
            sb.append(", field=").append(field)
              .append(", rejectedValue=").append(rejectedValue)
              .append(", reason=").append(reason);
        }
        sb.append("]");
        return sb.toString();
    }

    public static InvalidRequestException forNullField(String fieldName) {
        return new InvalidRequestException(
            "El campo '" + fieldName + "' no puede ser nulo",
            fieldName,
            null,
            "Campo obligatorio no proporcionado"
        );
    }

    public static InvalidRequestException forEmptyField(String fieldName) {
        return new InvalidRequestException(
            "El campo '" + fieldName + "' no puede estar vacío",
            fieldName,
            "",
            "Campo obligatorio está vacío"
        );
    }

    public static InvalidRequestException forInvalidLength(String fieldName, int min, int max, int actual) {
        return new InvalidRequestException(
            "El campo '" + fieldName + "' debe tener entre " + min + " y " + max + " caracteres, pero tiene " + actual,
            fieldName,
            actual,
            "Longitud fuera del rango permitido"
        );
    }
}