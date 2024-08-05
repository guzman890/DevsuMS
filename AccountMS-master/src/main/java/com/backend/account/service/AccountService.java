package com.backend.account.service;

import com.backend.account.model.dto.CreateAccountDTO;
import com.backend.account.model.entity.AccountEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Flux<AccountEntity> findAll();
    Mono<AccountEntity> findById(Long id);
    Mono<AccountEntity> findByAccountNumber(String accountNumber);
    Flux<AccountEntity> findByClientId(Long clientId);
    Mono<AccountEntity> save(AccountEntity account);
    Mono<AccountEntity> createAccount(CreateAccountDTO createAccountDTO);
    Mono<Void> deleteById(Long id);
}