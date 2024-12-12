package com.moneda.back.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CreateTransactionHistoryDto {
    private String cvuUser;
    private String transactionType;
    private Double amount;
    private String details;
}
