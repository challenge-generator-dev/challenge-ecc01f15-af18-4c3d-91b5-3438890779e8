package com.pragma.cleanbackend.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestRepository {
    Request save(Request request);

    Optional<Request> findById(UUID id);

    List<Request> findAll();

    Optional<Request> findByClientIdAndDescriptionAndDetails(String clientId, String description, String details);

    void deleteById(UUID id);

    long count();
}