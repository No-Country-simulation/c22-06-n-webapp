package com.moneda.back.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CreateTransactionDto {
    private String cvuSender;
    private String cvuReceiver;
    private Double amount;
    private String details;
    private String message;
    private Integer transactionType_Id;
}
