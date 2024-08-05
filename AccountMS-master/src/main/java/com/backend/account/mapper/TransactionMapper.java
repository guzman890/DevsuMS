package com.backend.account.mapper;

import com.backend.account.model.dto.TransactionDTO;
import com.backend.account.model.entity.TransactionEntity;

public class TransactionMapper {

    public static TransactionDTO toDto(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transactionEntity.getId());
        transactionDTO.setDate(transactionEntity.getDate());
        transactionDTO.setTransactionType(transactionEntity.getTransactionType());
        transactionDTO.setAmount(transactionEntity.getAmount());
        transactionDTO.setBalance(transactionEntity.getBalance());
        transactionDTO.setAccountId(transactionEntity.getAccountId());

        return transactionDTO;
    }

    public static TransactionEntity toEntity(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transactionDTO.getId());
        transactionEntity.setDate(transactionDTO.getDate());
        transactionEntity.setTransactionType(transactionDTO.getTransactionType());
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setBalance(transactionDTO.getBalance());
        return transactionEntity;
    }
}
