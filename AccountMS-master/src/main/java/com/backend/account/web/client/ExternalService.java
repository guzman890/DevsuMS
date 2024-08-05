package com.backend.account.web.client;

import com.backend.account.web.config.ExternalServiceConfig;
import com.backend.account.web.model.ClientDTO;
import com.backend.account.web.model.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class ExternalService {

    @Autowired
    private Builder webClientBuilder;

    @Autowired
    private ExternalServiceConfig externalServiceConfig;

    public Mono<Boolean> verifyClientId(Long clientId) {
        return webClientBuilder.build()
                .get()
                .uri(externalServiceConfig.getExternalServiceClientUri() + "/{id}", clientId)
                .retrieve()
                .bodyToMono(ClientDTO.class)
                .map(response -> response.getId().equals(clientId));
    }

    public Mono<ClientDTO> getClientById(Long clientId) {
        return webClientBuilder.build()
                .get()
                .uri(externalServiceConfig.getExternalServiceClientUri() + "/{id}", clientId)
                .retrieve()
                .bodyToMono(ClientDTO.class);
    }

    public Mono<PersonDTO> getPersonById(Long personId) {
        return webClientBuilder.build()
                .get()
                .uri(externalServiceConfig.getExternalServicePersonUri() + "/{id}", personId)
                .retrieve()
                .bodyToMono(PersonDTO.class);
    }


    public Mono<ClientDTO> verifyClientByName(String name) {
    return webClientBuilder.build()
            .post()
            .uri(externalServiceConfig.getExternalServiceClientUri() + "/name")
            .bodyValue(Collections.singletonMap("name", name))
            .retrieve()
            .bodyToMono(ClientDTO.class);
    }
}