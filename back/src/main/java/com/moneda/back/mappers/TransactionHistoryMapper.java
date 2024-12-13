package com.moneda.back.mappers;

import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.TransactionHistory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHistoryMapper {
    public List<TransactionHistoryDto> toTransactionHistoryDto(List<TransactionHistory> histories) {
        return histories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TransactionHistoryDto toDto(TransactionHistory history) {
        TransactionHistoryDto dto = new TransactionHistoryDto();
        dto.setCvuUser(history.getCvuUser());
        dto.setTransactionType(history.getTransactionType());
        dto.setAmount(history.getAmount());
        dto.setDetails(history.getDetails());
        dto.setTransactionDate(history.getTransactionDate());
        return dto;
    }
}
