package com.moneda.back.mappers;

import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class DepositHistoryMapper {
    public TransactionHistoryDto createHistoryDto(Transaction transaction) {
        if (transaction.getCvuSender() != null) {
            throw new IllegalArgumentException("El CVU del remitente debe ser nulo para los depósitos");
        }

        TransactionHistoryDto dto = new TransactionHistoryDto();
        dto.setCvuUser(transaction.getCvuReceiver());
        dto.setTransactionType(getTransactionTypeName(transaction));
        dto.setAmount(transaction.getAmount());
        dto.setDetails(transaction.getDetails());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }

    private String getTransactionTypeName(Transaction transaction) {
        if (transaction.getTransactionType() == null || transaction.getTransactionType().getName() == null) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo");
        }
        return transaction.getTransactionType().getName();
    }
}
