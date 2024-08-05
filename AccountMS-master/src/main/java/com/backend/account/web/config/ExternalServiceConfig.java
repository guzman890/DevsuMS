package com.backend.account.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalServiceConfig {

    @Value("${external.service.uri.client}")
    private String externalServiceClientUri;

    @Value("${external.service.uri.person}")
    private String externalServicePersonUri;

    public String getExternalServiceClientUri() {
        return externalServiceClientUri;
    }

    public String getExternalServicePersonUri() {
        return externalServicePersonUri;
    }
}