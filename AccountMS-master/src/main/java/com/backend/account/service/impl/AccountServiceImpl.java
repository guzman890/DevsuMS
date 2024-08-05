package com.backend.account.service.impl;

import com.backend.account.model.dto.CreateAccountDTO;
import com.backend.account.model.entity.AccountEntity;
import com.backend.account.repository.AccountRepository;
import com.backend.account.service.AccountService;
import com.backend.account.web.client.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExternalService externalServiceClient;

    @Override
    public Flux<AccountEntity> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<AccountEntity> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Mono<AccountEntity> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Flux<AccountEntity> findByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    @Override
    public Mono<AccountEntity> createAccount(CreateAccountDTO createAccountDTO){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(createAccountDTO.getAccountNumber());
        accountEntity.setAccountType(createAccountDTO.getAccountType());
        accountEntity.setInitialBalance(createAccountDTO.getInitialBalance());
        accountEntity.setStatus(createAccountDTO.getStatus());

        return externalServiceClient.verifyClientByName(createAccountDTO.getName())
                .flatMap(client -> {
                    accountEntity.setClientId(client.getId());
                    return this.save(accountEntity);
                });
    }


    @Override
    public Mono<AccountEntity> save(AccountEntity account) {
        return externalServiceClient.verifyClientId(account.getClientId())
                .flatMap(exists -> {
                    if (exists) {
                        return accountRepository.save(account);
                    } else {
                        return Mono.error(new RuntimeException("Client ID does not exist"));
                    }
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return accountRepository.deleteById(id);
    }
}
