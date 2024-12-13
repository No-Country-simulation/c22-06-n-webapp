package com.moneda.back.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateTransactionDto {
    private String cvuSender;
    private String cvuReceiver;
    private BigDecimal amount;
    private String message;
    private Integer transactionType_Id;
}
