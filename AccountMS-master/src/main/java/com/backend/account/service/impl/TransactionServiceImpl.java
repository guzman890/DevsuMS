package com.backend.account.service.impl;

import com.backend.account.business.TransactionBusiness;
import com.backend.account.model.dto.CreateTransactionDTO;
import com.backend.account.model.entity.AccountEntity;
import com.backend.account.model.entity.TransactionEntity;
import com.backend.account.repository.TransactionRepository;
import com.backend.account.service.AccountService;
import com.backend.account.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionBusiness transactionBusiness;

    @Override
    public Flux<TransactionEntity> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<TransactionEntity> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Flux<TransactionEntity> findByAccountIdAndDateBetween(Long accountId, ZonedDateTime date, ZonedDateTime date2) {
        return transactionRepository.findByAccountIdAndDateBetween(accountId, date, date2);
    }

    @Override
    public Mono<TransactionEntity> save(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<TransactionEntity> createTransaction(CreateTransactionDTO createTransactionDTO) {
        return accountService.findByAccountNumber(createTransactionDTO.getAccountNumber())
                .flatMap(account -> {
                    TransactionEntity transaction = new TransactionEntity();
                    transaction.setDate(ZonedDateTime.now());
                    transaction.setTransactionType("");
                    transaction.setAmount(createTransactionDTO.getAmount());
                    transaction.setBalance(createTransactionDTO.getInitialBalance());
                    transaction.setAccountId(account.getId());

                    AccountEntity updateAccount = transactionBusiness.executeTransaction(transaction, account);

                    return transactionRepository.save(transaction).flatMap(
                            savedTransaction -> accountService.save(updateAccount).thenReturn(savedTransaction));
                });
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return transactionRepository.deleteById(id);
    }
}
