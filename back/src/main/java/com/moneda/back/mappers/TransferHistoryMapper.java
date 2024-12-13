package com.moneda.back.mappers;

import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.Transaction;
import org.springframework.stereotype.Component;
@Component
public class TransferHistoryMapper {

    public TransactionHistoryDto createHistoryDtoForSender(Transaction transaction) {
        validateTransaction(transaction);
        TransactionHistoryDto dto = new TransactionHistoryDto();
        dto.setCvuUser(transaction.getCvuSender());
        dto.setTransactionType(getTransactionTypeName(transaction));
        dto.setAmount(transaction.getAmount().negate());
        dto.setDetails(transaction.getDetails());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }

    public TransactionHistoryDto createHistoryDtoForReceiver(Transaction transaction) {
        validateTransaction(transaction);
        TransactionHistoryDto dto = new TransactionHistoryDto();
        dto.setCvuUser(transaction.getCvuReceiver());
        dto.setTransactionType(getTransactionTypeName(transaction));
        dto.setAmount(transaction.getAmount());
        dto.setDetails(transaction.getDetails());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }

    private void validateTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        if (transaction.getTransactionType() == null || transaction.getTransactionType().getName() == null) {
            throw new IllegalArgumentException("Transaction type or name cannot be null");
        }
    }

    private String getTransactionTypeName(Transaction transaction) {
        return transaction.getTransactionType().getName();
    }
}