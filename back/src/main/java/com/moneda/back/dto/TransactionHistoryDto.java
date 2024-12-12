package com.moneda.back.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class TransactionHistoryDto {
    private String cvuUser;
    private String transactionType;
    private BigDecimal amount;
    private String details;
    private Date transactionDate;
}
