package com.moneda.back.mappers;

import com.moneda.back.dto.TransactionDto;
import com.moneda.back.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionMapper {
    public TransactionDto toTransactionDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setCvuSender(transaction.getCvuSender());
        dto.setCvuReceiver(transaction.getCvuReceiver());
        dto.setAmount(transaction.getAmount());
        dto.setDetails(transaction.getDetails());
        dto.setMessage(transaction.getMessage());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }
}