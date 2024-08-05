package com.backend.account.service.impl;

import com.backend.account.model.dto.AccountReportDTO;
import com.backend.account.model.dto.HeaderReportDTO;
import com.backend.account.model.dto.TransactionReportDTO;
import com.backend.account.service.AccountService;
import com.backend.account.service.ReportService;
import com.backend.account.service.TransactionService;
import com.backend.account.web.client.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ExternalService externalServiceClient;

    @Override
    public Mono<HeaderReportDTO> getReportByDateRangeAndClient(ZonedDateTime startDate, ZonedDateTime endDate, Long clientId) {
        return externalServiceClient.getClientById(clientId)
                .flatMap(client -> externalServiceClient.getPersonById(client.getPersonId())
                        .flatMap(person -> accountService.findByClientId(client.getId())
                                .flatMap(account -> transactionService.findByAccountIdAndDateBetween(account.getId(), startDate, endDate)
                                        .map(transaction -> {
                                            TransactionReportDTO transactionReportDTO = new TransactionReportDTO();
                                            transactionReportDTO.setTransactionType(transaction.getTransactionType());
                                            transactionReportDTO.setAmount(transaction.getAmount());
                                            transactionReportDTO.setBalance(transaction.getBalance());
                                            return transactionReportDTO;
                                        })
                                        .collectList()
                                        .map(transactions -> {
                                            AccountReportDTO accountReport = new AccountReportDTO();
                                            accountReport.setAccountNumber(account.getAccountNumber());
                                            accountReport.setType(account.getAccountType());
                                            accountReport.setTransactions(transactions);
                                            return accountReport;
                                        })
                                )
                                .collectList()
                                .map(accounts -> {
                                    HeaderReportDTO report = new HeaderReportDTO();
                                    report.setDate(new Date());
                                    report.setClient(person.getName());
                                    report.setAccounts(accounts);
                                    return report;
                                })
                        )
                );
    }
}
