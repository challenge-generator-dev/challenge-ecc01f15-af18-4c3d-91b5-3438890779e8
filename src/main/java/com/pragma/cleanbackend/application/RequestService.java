package com.pragma.cleanbackend.application;

import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.RequestRepository;
import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);
    private static final int MIN_DESCRIPTION_LENGTH = 10;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MIN_DETAILS_LENGTH = 5;
    private static final int MAX_DETAILS_LENGTH = 2000;

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        if (requestRepository == null) {
            throw new IllegalArgumentException("El repositorio de solicitudes no puede ser nulo");
        }
        this.requestRepository = requestRepository;
    }

    public Request createRequest(String clientId, String description, String details) {
        logger.info("Iniciando registro de solicitud para cliente: {}", clientId);

        validateClientId(clientId);
        validateDescription(description);
        validateDetails(details);

        Request newRequest = new Request(clientId, description, details);
        newRequest.validate();

        Optional<Request> existingRequest = requestRepository.findByClientIdAndDescriptionAndDetails(
            clientId, description, details
        );

        if (existingRequest.isPresent()) {
            logger.warn("Detectada solicitud duplicada para cliente: {}", clientId);
            throw DuplicateRequestException.forRequest(existingRequest.get());
        }

        Request savedRequest = requestRepository.save(newRequest);
        logger.info("Solicitud registrada exitosamente con ID: {}", savedRequest.getId());

        return savedRequest;
    }

    public Optional<Request> getRequestById(UUID id) {
        if (id == null) {
            logger.warn("Se intentó buscar una solicitud con ID nulo");
            return Optional.empty();
        }
        logger.debug("Buscando solicitud con ID: {}", id);
        return requestRepository.findById(id);
    }

    public List<Request> getAllRequests() {
        logger.debug("Obteniendo todas las solicitudes");
        return requestRepository.findAll();
    }

    public void deleteRequest(UUID id) {
        if (id == null) {
            throw InvalidRequestException.forNullField("id");
        }
        logger.info("Eliminando solicitud con ID: {}", id);
        requestRepository.deleteById(id);
    }

    public long getRequestCount() {
        return requestRepository.count();
    }

    private void validateClientId(String clientId) {
        if (clientId == null) {
            logger.error("Validation failed: clientId es nulo");
            throw InvalidRequestException.forNullField("clientId");
        }
        if (clientId.trim().isEmpty()) {
            logger.error("Validation failed: clientId está vacío");
            throw InvalidRequestException.forEmptyField("clientId");
        }
        if (clientId.length() > 100) {
            logger.error("Validation failed: clientId excede longitud máxima");
            throw InvalidRequestException.forInvalidLength("clientId", 1, 100, clientId.length());
        }
    }

    private void validateDescription(String description) {
        if (description == null) {
            logger.error("Validation failed: description es nulo");
            throw InvalidRequestException.forNullField("description");
        }
        if (description.trim().isEmpty()) {
            logger.error("Validation failed: description está vacío");
            throw InvalidRequestException.forEmptyField("description");
        }
        int length = description.length();
        if (length < MIN_DESCRIPTION_LENGTH || length > MAX_DESCRIPTION_LENGTH) {
            logger.error("Validation failed: description longitud inválida - {}", length);
            throw InvalidRequestException.forInvalidLength(
                "description", MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH, length
            );
        }
    }

    private void validateDetails(String details) {
        if (details == null) {
            logger.error("Validation failed: details es nulo");
            throw InvalidRequestException.forNullField("details");
        }
        if (details.trim().isEmpty()) {
            logger.error("Validation failed: details está vacío");
            throw InvalidRequestException.forEmptyField("details");
        }
        int length = details.length();
        if (length < MIN_DETAILS_LENGTH || length > MAX_DETAILS_LENGTH) {
            logger.error("Validation failed: details longitud inválida - {}", length);
            throw InvalidRequestException.forInvalidLength(
                "details", MIN_DETAILS_LENGTH, MAX_DETAILS_LENGTH, length
            );
        }
    }
}