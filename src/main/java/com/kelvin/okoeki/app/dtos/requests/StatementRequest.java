package com.kelvin.okoeki.app.dtos.requests;

import lombok.Data;

@Data
public class StatementRequest {
    private String accountNumber;
    private String firstName;
    private String lastName;

}