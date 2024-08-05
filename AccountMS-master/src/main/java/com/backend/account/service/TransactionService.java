package com.backend.account.service;

import com.backend.account.model.dto.CreateTransactionDTO;
import com.backend.account.model.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public interface TransactionService {
    Flux<TransactionEntity> findAll();
    Mono<TransactionEntity> findById(Long id);
    Flux<TransactionEntity> findByAccountIdAndDateBetween(Long accountId, ZonedDateTime date, ZonedDateTime date2);
    Mono<TransactionEntity> save(TransactionEntity transaction);
    Mono<TransactionEntity> createTransaction(CreateTransactionDTO createTransactionDTO);
    Mono<Void> deleteById(Long id);
}
