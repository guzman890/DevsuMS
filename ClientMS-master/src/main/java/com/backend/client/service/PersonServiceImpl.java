package com.backend.client.service;

import com.backend.client.model.entity.ClientEntity;
import com.backend.client.model.entity.PersonEntity;
import com.backend.client.repository.PersonRepository;
import com.backend.client.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Flux<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Mono<PersonEntity> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Mono<PersonEntity> findByName(String name){
        return personRepository.findByName(name);
    }


    @Override
    public Mono<PersonEntity> save(PersonEntity person) {
        return personRepository.save(person);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return personRepository.deleteById(id);
    }
}