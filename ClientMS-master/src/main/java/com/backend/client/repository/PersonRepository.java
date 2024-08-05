package com.backend.client.repository;

import com.backend.client.model.entity.ClientEntity;
import com.backend.client.model.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, Long> {
    Mono<PersonEntity> findByName(String name);
}