package com.backend.client.mapper;

import com.backend.client.model.dto.PersonDTO;
import com.backend.client.model.entity.PersonEntity;

public class PersonMapper {

    public static PersonDTO toDTO(PersonEntity personEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setName(personEntity.getName());
        personDTO.setGender(personEntity.getGender());
        personDTO.setAge(personEntity.getAge());
        personDTO.setIdentification(personEntity.getIdentification());
        personDTO.setAddress(personEntity.getAddress());
        personDTO.setPhone(personEntity.getPhone());
        return personDTO;
    }

    public static PersonEntity toEntity(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personDTO.getName());
        personEntity.setGender(personDTO.getGender());
        personEntity.setAge(personDTO.getAge());
        personEntity.setIdentification(personDTO.getIdentification());
        personEntity.setAddress(personDTO.getAddress());
        personEntity.setPhone(personDTO.getPhone());
        return personEntity;
    }
}