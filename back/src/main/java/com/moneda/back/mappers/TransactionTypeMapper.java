package com.moneda.back.mappers;

import com.moneda.back.dto.TransactionTypeDto;
import com.moneda.back.entities.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class TransactionTypeMapper {
    public TransactionTypeDto toCurrencyDto(TransactionType transactionType) {
        TransactionTypeDto dto = new TransactionTypeDto();
        dto.setId(transactionType.getId());
        dto.setName(transactionType.getName());
        dto.setCode(transactionType.getCode());
        return dto;
    }
}
