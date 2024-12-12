package com.moneda.back.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateTransactionHistoryDto {
    private String cvuUser;
    private String transactionType;
    private BigDecimal amount;
    private String details;
}
