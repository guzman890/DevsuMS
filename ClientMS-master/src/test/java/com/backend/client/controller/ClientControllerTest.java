package com.backend.client.controller;

import com.backend.client.model.dto.ClientByPersonDTO;
import com.backend.client.model.dto.ClientDTO;
import com.backend.client.model.dto.CreateUserDTO;
import com.backend.client.model.entity.ClientEntity;
import com.backend.client.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test creating a user")
    public void testCreateUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("John Doe");
        createUserDTO.setAddress("123 Main St");
        createUserDTO.setPhone("555-1234");
        createUserDTO.setPassword("securepassword");
        createUserDTO.setStatus("active");

        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");

        when(clientService.createUser(any(CreateUserDTO.class))).thenReturn(Mono.just(client));

        Mono<ResponseEntity<ClientDTO>> response = clientController.createUser(createUserDTO);

        assertNotNull(response);
        assertEquals(200, response.block().getStatusCodeValue());
        assertEquals("securepassword", response.block().getBody().getPassword());
        assertEquals("active", response.block().getBody().getStatus());
    }

    @Test
    @DisplayName("Test getting a client by ID")
    public void testGetClientById() {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");

        when(clientService.findById(1L)).thenReturn(Mono.just(client));

        Mono<ResponseEntity<ClientDTO>> response = clientController.getClientById(1L);

        assertNotNull(response);
        assertEquals(200, response.block().getStatusCodeValue());
        assertEquals("securepassword", response.block().getBody().getPassword());
        assertEquals("active", response.block().getBody().getStatus());
    }

    @Test
    @DisplayName("Test getting a client by person name")
    public void testGetClientByPersonName() {
        ClientByPersonDTO clientByPersonDTO = new ClientByPersonDTO();
        clientByPersonDTO.setName("John Doe");

        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");

        when(clientService.findByPersonName("John Doe")).thenReturn(Mono.just(client));

        Mono<ResponseEntity<ClientDTO>> response = clientController.getClientByPersonName(clientByPersonDTO);

        assertNotNull(response);
        assertEquals(200, response.block().getStatusCodeValue());
        assertEquals("securepassword", response.block().getBody().getPassword());
        assertEquals("active", response.block().getBody().getStatus());
    }

    @Test
    @DisplayName("Test updating a client")
    public void testUpdateClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setPassword("newpassword");
        clientDTO.setStatus("inactive");

        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");

        when(clientService.findById(1L)).thenReturn(Mono.just(client));
        when(clientService.save(any(ClientEntity.class))).thenReturn(Mono.just(client));

        Mono<ResponseEntity<ClientDTO>> response = clientController.updateClient(1L, clientDTO);

        assertNotNull(response);
        assertEquals(200, response.block().getStatusCodeValue());
        assertEquals("newpassword", response.block().getBody().getPassword());
        assertEquals("inactive", response.block().getBody().getStatus());
    }

    @Test
    @DisplayName("Test deleting a client by ID")
    public void testDeleteClient() {
        when(clientService.deleteById(1L)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> response = clientController.deleteClient(1L);

        assertNotNull(response);
        assertEquals(204, response.block().getStatusCodeValue());
    }
}