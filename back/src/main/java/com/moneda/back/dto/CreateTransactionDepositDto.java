package com.moneda.back.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDepositDto {
    private String cvuReceiver;
    private BigDecimal amount;
    private String message;
}
