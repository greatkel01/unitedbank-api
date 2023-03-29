package com.kelvin.okoeki.app.controller;

import com.kelvin.okoeki.app.dtos.requests.StatementRequest;
import com.kelvin.okoeki.app.dtos.requests.TransferBalanceRequest;
import com.kelvin.okoeki.app.model.Account;
import com.kelvin.okoeki.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/united-bank/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.save(account));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> allAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/search/{accountNumber}")
    public ResponseEntity<Account> searchByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }

    @GetMapping("/deposit/{accountNumber}/{amount}")
    public ResponseEntity deposit(@PathVariable String accountNumber, @PathVariable double amount) {
        return ResponseEntity.ok(accountService.deposit(accountNumber, amount));
    }

    @GetMapping("/withdrawal/{accountNumber}/{amount}")
    public ResponseEntity withdrawal(@PathVariable String accountNumber, @PathVariable double amount) {
        return ResponseEntity.ok(accountService.withdraw(accountNumber, amount));
    }

    @PostMapping("/transfer-funds")
    public ResponseEntity transferFunds(@RequestBody TransferBalanceRequest transferBalanceRequest) {
        return ResponseEntity.ok(accountService.transferFunds(transferBalanceRequest));
    }

    @PostMapping("/statement")
    public ResponseEntity getStatement(
            @RequestBody StatementRequest statementRequest) {
        return ResponseEntity.ok(accountService.getStatement(statementRequest.getAccountNumber()));

    }

}
