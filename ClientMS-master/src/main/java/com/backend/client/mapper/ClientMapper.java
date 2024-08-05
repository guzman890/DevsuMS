package com.backend.client.mapper;

import com.backend.client.model.dto.ClientDTO;
import com.backend.client.model.entity.ClientEntity;

public class ClientMapper {

    public static ClientDTO toDTO(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientEntity.getId());
        clientDTO.setPassword(clientEntity.getPassword());
        clientDTO.setStatus(clientEntity.getStatus());
        clientDTO.setPersonId(clientEntity.getPersonId());
        return clientDTO;
    }

    public static ClientEntity toEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientDTO.getId());
        clientEntity.setPassword(clientDTO.getPassword());
        clientEntity.setStatus(clientDTO.getStatus());
        clientEntity.setPersonId(clientDTO.getPersonId());
        return clientEntity;
    }
}