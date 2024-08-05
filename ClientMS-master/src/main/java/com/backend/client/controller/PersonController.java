package com.backend.client.controller;

import com.backend.client.model.dto.PersonDTO;
import com.backend.client.model.entity.PersonEntity;
import com.backend.client.service.PersonService;
import com.backend.client.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public Flux<PersonDTO> getAllPersons() {
        return personService.findAll().map(PersonMapper::toDTO);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonDTO>> getPersonById(@PathVariable Long id) {
        return personService.findById(id)
                .map(person -> ResponseEntity.ok(PersonMapper.toDTO(person)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<PersonDTO>> createPerson(@RequestBody PersonDTO personDTO) {
        PersonEntity person = PersonMapper.toEntity(personDTO);
        return personService.save(person)
                .map(savedPerson -> ResponseEntity.ok(PersonMapper.toDTO(savedPerson)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PersonDTO>> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return personService.findById(id)
                .flatMap(person -> {
                    person.setName(personDTO.getName());
                    person.setGender(personDTO.getGender());
                    person.setAge(personDTO.getAge());
                    person.setIdentification(personDTO.getIdentification());
                    person.setAddress(personDTO.getAddress());
                    person.setPhone(personDTO.getPhone());
                    return personService.save(person);
                })
                .map(updatedPerson -> ResponseEntity.ok(PersonMapper.toDTO(updatedPerson)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<PersonDTO>> patchPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return personService.findById(id)
                .flatMap(person -> {
                    if (personDTO.getName() != null) {
                        person.setName(personDTO.getName());
                    }
                    if (personDTO.getGender() != null) {
                        person.setGender(personDTO.getGender());
                    }
                    if (personDTO.getAge() != 0) {
                        person.setAge(personDTO.getAge());
                    }
                    if (personDTO.getIdentification() != null) {
                        person.setIdentification(personDTO.getIdentification());
                    }
                    if (personDTO.getAddress() != null) {
                        person.setAddress(personDTO.getAddress());
                    }
                    if (personDTO.getPhone() != null) {
                        person.setPhone(personDTO.getPhone());
                    }
                    return personService.save(person);
                })
                .map(updatedPerson -> ResponseEntity.ok(PersonMapper.toDTO(updatedPerson)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePerson(@PathVariable Long id) {
        return personService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}