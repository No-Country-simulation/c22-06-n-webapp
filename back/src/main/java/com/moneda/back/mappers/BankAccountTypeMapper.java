package com.moneda.back.mappers;

import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.entities.BankAccountType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountTypeMapper {
    private final CurrencyMapper currencyMapper;
    public BankAccountTypeDto toBankAccountTypeDto(BankAccountType bankAccountType) {
        BankAccountTypeDto dto = new BankAccountTypeDto();
        dto.setName(bankAccountType.getName());
        dto.setCode(bankAccountType.getCode());
        dto.setCurrency(currencyMapper.toCurrencyDto(bankAccountType.getCurrency()));
        return dto;
    }
}
