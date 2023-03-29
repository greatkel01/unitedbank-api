package com.kelvin.okoeki.app.dtos;

import com.kelvin.okoeki.app.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountStatement {
    BigDecimal currentBalance;
    List<Transaction> transactionHistory;
}
