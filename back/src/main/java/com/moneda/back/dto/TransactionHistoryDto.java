package com.moneda.back.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
@Data
public class TransactionHistoryDto {
    private String cvuUser;
    private String transactionType;
    private Double amount;
    private String details;
    private Date transactionDate;
}
