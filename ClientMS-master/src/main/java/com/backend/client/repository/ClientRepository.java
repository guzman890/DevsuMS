package com.backend.client.repository;

import com.backend.client.model.entity.ClientEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, Long> {
    Mono<ClientEntity> findByPersonId(Long personId);
}