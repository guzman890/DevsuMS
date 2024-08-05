package com.backend.client.service;

import com.backend.client.model.dto.CreateUserDTO;
import com.backend.client.model.entity.ClientEntity;
import com.backend.client.model.entity.PersonEntity;
import com.backend.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PersonService personService;

    @InjectMocks
    private ClientServiceImpl clientService;

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

        PersonEntity person = new PersonEntity();
        person.setId(1L);
        person.setName("John Doe");

        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");
        client.setPersonId(person.getId());

        when(personService.save(any(PersonEntity.class))).thenReturn(Mono.just(person));
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(Mono.just(client));

        Mono<ClientEntity> result = clientService.createUser(createUserDTO);

        assertNotNull(result);
        assertEquals("securepassword", result.block().getPassword());
        assertEquals("active", result.block().getStatus());
        assertEquals(person.getId(), result.block().getPersonId());
    }

    @Test
    @DisplayName("Test finding a client by ID")
    public void testFindById() {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");

        when(clientRepository.findById(1L)).thenReturn(Mono.just(client));

        Mono<ClientEntity> result = clientService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.block().getId());
        assertEquals("securepassword", result.block().getPassword());
        assertEquals("active", result.block().getStatus());
    }

    @Test
    @DisplayName("Test finding a client by person name")
    public void testFindByPersonName() {
        PersonEntity person = new PersonEntity();
        person.setId(1L);
        person.setName("John Doe");

        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");
        client.setPersonId(person.getId());

        when(personService.findByName("John Doe")).thenReturn(Mono.just(person));
        when(clientRepository.findByPersonId(1L)).thenReturn(Mono.just(client));

        Mono<ClientEntity> result = clientService.findByPersonName("John Doe");

        assertNotNull(result);
        assertEquals(1L, result.block().getId());
        assertEquals("securepassword", result.block().getPassword());
        assertEquals("active", result.block().getStatus());
        assertEquals(person.getId(), result.block().getPersonId());
    }

    @Test
    @DisplayName("Test saving a client")
    public void testSave() {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setPassword("securepassword");
        client.setStatus("active");

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(Mono.just(client));

        Mono<ClientEntity> result = clientService.save(client);

        assertNotNull(result);
        assertEquals(1L, result.block().getId());
        assertEquals("securepassword", result.block().getPassword());
        assertEquals("active", result.block().getStatus());
    }

    @Test
    @DisplayName("Test deleting a client by ID")
    public void testDeleteById() {
        when(clientRepository.deleteById(1L)).thenReturn(Mono.empty());

        Mono<Void> result = clientService.deleteById(1L);

        assertNotNull(result);
        assertEquals(Mono.empty(), result);
    }
}