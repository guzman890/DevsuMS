package com.backend.account.repository;

import com.backend.account.model.entity.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.ZonedDateTime;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<TransactionEntity, Long> {
    Flux<TransactionEntity> findByAccountIdAndDateBetween(Long accountId, ZonedDateTime date, ZonedDateTime date2);
}