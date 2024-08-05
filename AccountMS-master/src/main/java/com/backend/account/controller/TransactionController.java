package com.backend.account.controller;

import com.backend.account.model.dto.CreateTransactionDTO;
import com.backend.account.model.dto.TransactionDTO;
import com.backend.account.model.entity.TransactionEntity;
import com.backend.account.mapper.TransactionMapper;
import com.backend.account.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<TransactionDTO> getAllTransactions() {
        return transactionService.findAll()
                .map(TransactionMapper::toDto);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TransactionDTO>> getTransactionById(@PathVariable Long id) {
        return transactionService.findById(id)
                .map(transaction -> ResponseEntity.ok(TransactionMapper.toDto(transaction)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<TransactionDTO>> saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = TransactionMapper.toEntity(transactionDTO);
        return transactionService.save(transactionEntity)
                .map(savedTransaction -> ResponseEntity.ok(TransactionMapper.toDto(savedTransaction)));
    }

    @PostMapping("/createTransaction")
    public Mono<ResponseEntity<TransactionDTO>> createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) {
        return transactionService.createTransaction(createTransactionDTO)
                .map(savedTransaction -> ResponseEntity.ok(TransactionMapper.toDto(savedTransaction)));
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<TransactionDTO>> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        return transactionService.findById(id)
                .flatMap(existingTransaction -> {
                    TransactionEntity transactionEntity = TransactionMapper.toEntity(transactionDTO);
                    transactionEntity.setId(id);
                    return transactionService.save(transactionEntity)
                            .map(TransactionMapper::toDto)
                            .map(ResponseEntity::ok);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }
}