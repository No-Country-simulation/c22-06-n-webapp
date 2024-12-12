package com.moneda.back.mappers;

import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.TransactionHistory;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TransactionHistoryMapper {
    public TransactionHistoryDto toCurrencyDto(TransactionHistory transactionHistory) {
        TransactionHistoryDto dto = new TransactionHistoryDto();
        dto.setCvuUser(transactionHistory.getCvuUser());
        dto.setTransactionType(transactionHistory.getTransactionType());
        dto.setAmount(transactionHistory.getAmount());
        dto.setDetails(transactionHistory.getDetails());
        dto.setTransactionDate(transactionHistory.getTransactionDate());
        return dto;
    }
}