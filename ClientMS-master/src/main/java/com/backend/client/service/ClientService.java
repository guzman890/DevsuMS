package com.backend.client.service;

import com.backend.client.model.dto.CreateUserDTO;
import com.backend.client.model.entity.ClientEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<ClientEntity> findAll();
    Mono<ClientEntity> findById(Long id);
    Mono<ClientEntity> findByPersonName(String name);
    Mono<ClientEntity> findByPersonId(Long id);
    Mono<ClientEntity> save(ClientEntity client);
    Mono<ClientEntity> createUser(CreateUserDTO createUserDTO);
    Mono<Void> deleteById(Long id);
}