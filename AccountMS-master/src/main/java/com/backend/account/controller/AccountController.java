package com.backend.account.controller;

import com.backend.account.model.dto.AccountDTO;
import com.backend.account.model.dto.CreateAccountDTO;
import com.backend.account.model.entity.AccountEntity;
import com.backend.account.mapper.AccountMapper;
import com.backend.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<AccountDTO> getAllAccounts() {
        return accountService.findAll()
                .map(AccountMapper::toDto);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AccountDTO>> getAccountById(@PathVariable Long id) {
        return accountService.findById(id)
                .map(account -> ResponseEntity.ok(AccountMapper.toDto(account)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<AccountDTO>> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountEntity accountEntity = AccountMapper.toEntity(accountDTO);
        return accountService.save(accountEntity)
                .map( savedAccount -> ResponseEntity.ok(AccountMapper.toDto(savedAccount)));
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<AccountDTO>> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        return accountService.createAccount(createAccountDTO)
                .map(savedAccount -> ResponseEntity.ok(AccountMapper.toDto(savedAccount)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<AccountDTO>> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        return accountService.findById(id)
                .flatMap(existingAccount -> {
                    AccountEntity accountEntity = AccountMapper.toEntity(accountDTO);
                    accountEntity.setId(id);
                    return accountService.save(accountEntity)
                            .map(AccountMapper::toDto)
                            .map(ResponseEntity::ok);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAccount(@PathVariable Long id) {
        return accountService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
