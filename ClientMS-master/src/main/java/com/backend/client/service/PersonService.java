package com.backend.client.service;

import com.backend.client.model.entity.PersonEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Flux<PersonEntity> findAll();
    Mono<PersonEntity> findById(Long id);
    Mono<PersonEntity> findByName(String name);
    Mono<PersonEntity> save(PersonEntity person);
    Mono<Void> deleteById(Long id);
}
