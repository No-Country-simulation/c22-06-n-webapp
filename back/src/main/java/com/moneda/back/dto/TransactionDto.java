package com.moneda.back.dto;

import com.moneda.back.entities.TransactionType;
import lombok.Data;

import java.util.Date;
@Data
public class TransactionDto {
    private String cvuSender;
    private String cvuReceiver;
    private Double amount;
    private Date transactionDate;
    private String details;
    private String message;
    private TransactionType transactionType;
}
