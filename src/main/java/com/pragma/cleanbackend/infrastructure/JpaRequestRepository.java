package com.pragma.cleanbackend.infrastructure;

import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.RequestRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaRequestRepository implements RequestRepository {

    public JpaRequestRepository() {
    }

    @Override
    public Request save(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        return request;
    }

    @Override
    public Optional<Request> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return Optional.empty();
    }

    @Override
    public List<Request> findAll() {
        return List.of();
    }

    @Override
    public Optional<Request> findByClientIdAndDescriptionAndDetails(String clientId, String description, String details) {
        if (clientId == null || description == null || details == null) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
    }

    @Override
    public long count() {
        return 0L;
    }
}