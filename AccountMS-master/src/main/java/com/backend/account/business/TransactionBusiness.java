package com.backend.account.business;

import com.backend.account.exception.InsufficientFundsException;
import com.backend.account.exception.InvalidTransactionException;
import com.backend.account.model.entity.AccountEntity;
import com.backend.account.model.entity.TransactionEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionBusiness {

    public AccountEntity executeTransaction(TransactionEntity transaction, AccountEntity account) {
        validateTransaction(transaction, account);
        account.setInitialBalance(account.getInitialBalance() + transaction.getAmount());
        return account;
    }

    public void validateTransaction(TransactionEntity transaction, AccountEntity account) {
        if (transaction.getAmount() == 0) {
            throw new InvalidTransactionException("Amount not must be 0");
        }
        if ( account.getInitialBalance() + transaction.getAmount() < 0) {
            throw new InsufficientFundsException("insufficient funds");
        }
    }
}
