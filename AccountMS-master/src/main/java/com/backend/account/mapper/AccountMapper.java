package com.backend.account.mapper;

import com.backend.account.model.dto.AccountDTO;
import com.backend.account.model.entity.AccountEntity;

public class AccountMapper {

    public static AccountDTO toDto(AccountEntity accountEntity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountEntity.getId());
        accountDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountDTO.setAccountType(accountEntity.getAccountType());
        accountDTO.setInitialBalance(accountEntity.getInitialBalance());
        accountDTO.setStatus(accountEntity.getStatus());
        accountDTO.setClientId(accountEntity.getClientId());
        return accountDTO;
    }

    public static AccountEntity toEntity(AccountDTO accountDTO) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountDTO.getId());
        accountEntity.setAccountNumber(accountDTO.getAccountNumber());
        accountEntity.setAccountType(accountDTO.getAccountType());
        accountEntity.setInitialBalance(accountDTO.getInitialBalance());
        accountEntity.setStatus(accountDTO.getStatus());
        accountEntity.setClientId(accountDTO.getClientId());
        return accountEntity;
    }
}