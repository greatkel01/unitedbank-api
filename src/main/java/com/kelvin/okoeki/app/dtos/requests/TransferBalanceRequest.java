package com.kelvin.okoeki.app.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBalanceRequest {
    private String fromAccountNumber;

    private String toAccountNumber;

    private BigDecimal amount;

}
