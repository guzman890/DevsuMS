package com.backend.client.controller;

import com.backend.client.model.dto.CreateUserDTO;
import com.backend.client.model.entity.ClientEntity;
import com.backend.client.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Test creating a user")
    public void testCreateUser() throws Exception {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("John Doe");
        createUserDTO.setAddress("123 Main St");
        createUserDTO.setPhone("555-1234");
        createUserDTO.setPassword("securepassword");
        createUserDTO.setStatus("true");

        mockMvc.perform(post("/clients/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDTO)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test getting a client by ID")
    public void testGetClientById() throws Exception {
        ClientEntity client = new ClientEntity();
        client.setPassword("securepassword");
        client.setStatus("true");
        client.setPersonId(1L);
        client = clientRepository.save(client).block();

        mockMvc.perform(get("/clients/" + client.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}