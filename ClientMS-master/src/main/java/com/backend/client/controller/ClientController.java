package com.backend.client.controller;

import com.backend.client.model.dto.ClientByPersonDTO;
import com.backend.client.model.dto.ClientDTO;
import com.backend.client.model.dto.CreateUserDTO;
import com.backend.client.model.entity.ClientEntity;
import com.backend.client.service.ClientService;
import com.backend.client.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Flux<ClientDTO> getAllClients() {
        return clientService.findAll().map(ClientMapper::toDTO);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClientDTO>> getClientById(@PathVariable Long id) {
        return clientService.findById(id)
                .map(client -> ResponseEntity.ok(ClientMapper.toDTO(client)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/name")
    public Mono<ResponseEntity<ClientDTO>> getClientByPersonName(@RequestBody ClientByPersonDTO clientByPersonDTO) {
        return clientService.findByPersonName(clientByPersonDTO.getName())
                .map(client -> ResponseEntity.ok(ClientMapper.toDTO(client)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ClientDTO>> createClient(@RequestBody ClientDTO clientDTO) {
        ClientEntity client = ClientMapper.toEntity(clientDTO);
        return clientService.save(client)
                .map(savedClient -> ResponseEntity.ok(ClientMapper.toDTO(savedClient)));
    }

    @PostMapping("/createUser")
    public Mono<ResponseEntity<ClientDTO>> createUser(@RequestBody CreateUserDTO createUserDTO) {

        return clientService.createUser(createUserDTO)
                .map(savedClient -> ResponseEntity.ok(ClientMapper.toDTO(savedClient)));

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ClientDTO>> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.findById(id)
                .flatMap(client -> {
                    client.setPassword(clientDTO.getPassword());
                    client.setStatus(clientDTO.getStatus());
                    return clientService.save(client);
                })
                .map(updatedClient -> ResponseEntity.ok(ClientMapper.toDTO(updatedClient)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<ClientDTO>> patchClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.findById(id)
                .flatMap(client -> {
                    if (clientDTO.getPassword() != null) {
                        client.setPassword(clientDTO.getPassword());
                    }
                    if (clientDTO.getStatus() != null) {
                        client.setStatus(clientDTO.getStatus());
                    }
                    return clientService.save(client);
                })
                .map(updatedClient -> ResponseEntity.ok(ClientMapper.toDTO(updatedClient)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable Long id) {
        return clientService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}