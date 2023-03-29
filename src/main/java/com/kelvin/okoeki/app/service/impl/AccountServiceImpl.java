package com.kelvin.okoeki.app.service.impl;

import com.kelvin.okoeki.app.dtos.AccountStatement;
import com.kelvin.okoeki.app.dtos.requests.TransferBalanceRequest;
import com.kelvin.okoeki.app.model.Account;
import com.kelvin.okoeki.app.model.Transaction;
import com.kelvin.okoeki.app.repository.AccountRepository;
import com.kelvin.okoeki.app.repository.TransactionRepository;
import com.kelvin.okoeki.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    public Account save(Account account) {
        account.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);
        return accountRepository.findByAccountNumberEquals(account.getAccountNumber());
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        return account;
    }


    @Override
    public Transaction transferFunds(
            TransferBalanceRequest transferBalanceRequest
    ) {
        String fromAccountNumber = transferBalanceRequest.getFromAccountNumber();
        String toAccountNumber = transferBalanceRequest.getToAccountNumber();
        BigDecimal amount = transferBalanceRequest.getAmount();
        Account fromAccount = accountRepository.findByAccountNumberEquals(
                fromAccountNumber
        );
        Account toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);
        if (fromAccount.getCurrentBalance().compareTo(BigDecimal.ONE) == 1
                && fromAccount.getCurrentBalance().compareTo(amount) == 1
        ) {
            fromAccount.setCurrentBalance(fromAccount.getCurrentBalance().subtract(amount));
            accountRepository.save(fromAccount);
            toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(amount));
            accountRepository.save(toAccount);
            Transaction transaction = transactionRepository.save(new Transaction(0L, fromAccountNumber, amount, new Timestamp(System.currentTimeMillis())));
            return transaction;
        }
        return null;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        if (account != null && account.getCurrentBalance().doubleValue() >= amount) {
            account.setCurrentBalance(BigDecimal.valueOf(account.getCurrentBalance().doubleValue() - amount));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        if (account != null) {
            account.setCurrentBalance(BigDecimal.valueOf(account.getCurrentBalance().doubleValue() + amount));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public AccountStatement getStatement(String accountNumber) {
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        return new AccountStatement(account.getCurrentBalance(), transactionRepository.findByAccountNumberEquals(accountNumber));
    }
}