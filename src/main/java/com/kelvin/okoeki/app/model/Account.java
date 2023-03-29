package com.kelvin.okoeki.app.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private String accountNumber;

    private String firstName;

    private String lastName;

    private BigDecimal currentBalance;

    private Timestamp createdTime;
}
