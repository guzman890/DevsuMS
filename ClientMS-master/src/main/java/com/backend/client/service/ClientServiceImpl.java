package com.backend.client.service;

import com.backend.client.mapper.ClientMapper;
import com.backend.client.model.dto.ClientDTO;
import com.backend.client.model.dto.CreateUserDTO;
import com.backend.client.model.entity.ClientEntity;
import com.backend.client.model.entity.PersonEntity;
import com.backend.client.repository.ClientRepository;
import com.backend.client.service.ClientService;
import com.backend.client.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonService personService;

    @Override
    public Flux<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<ClientEntity> findByPersonName(String name) {
        return personService.findByName(name)
                .flatMap(person -> this.findByPersonId(person.getId()));
    }

    @Override
    public Mono<ClientEntity> findByPersonId(Long id) {
        return clientRepository.findByPersonId(id);
    }

    @Override
    public Mono<ClientEntity> save(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<ClientEntity> createUser(CreateUserDTO createUserDTO) {
        PersonEntity personEntity = new PersonEntity();

        personEntity.setGender("unknown");
        personEntity.setAge(18);
        personEntity.setIdentification("XXXXXXXX");

        personEntity.setName(createUserDTO.getName());
        personEntity.setAddress(createUserDTO.getAddress());
        personEntity.setPhone(createUserDTO.getPhone());

        return personService.save(personEntity)
                .flatMap(savedPerson -> {
                    ClientEntity clientEntity = new ClientEntity();
                    clientEntity.setPassword(createUserDTO.getPassword());
                    clientEntity.setStatus(createUserDTO.getStatus());
                    clientEntity.setPersonId(savedPerson.getId());

                    return this.save(clientEntity);
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return clientRepository.deleteById(id);
    }
}