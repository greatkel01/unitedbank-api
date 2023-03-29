package com.kelvin.okoeki.app.service;

import com.kelvin.okoeki.app.dtos.AccountStatement;
import com.kelvin.okoeki.app.dtos.requests.TransferBalanceRequest;
import com.kelvin.okoeki.app.model.Account;
import com.kelvin.okoeki.app.model.Transaction;

import java.util.List;

public interface  AccountService {
    List<Account> findAll();
    Account save(Account account);
    Transaction transferFunds(
            TransferBalanceRequest transferBalanceRequest
    );
    AccountStatement getStatement(String accountNumber);

    Account findByAccountNumber(String accountNumber);

    public boolean withdraw(String accountNumber, double amount);

    public boolean deposit(String accountNumber, double amount);
}