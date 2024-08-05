package com.backend.account.repository;

import com.backend.account.model.entity.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
    Mono<AccountEntity> findByAccountNumber(String accountNumber);
    Flux<AccountEntity> findByClientId(Long ClientId);
}