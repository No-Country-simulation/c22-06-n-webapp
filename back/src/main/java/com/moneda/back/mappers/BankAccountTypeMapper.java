package com.moneda.back.mappers;

import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.entities.BankAccountType;
import org.springframework.stereotype.Component;

@Component
public class BankAccountTypeMapper {
    public BankAccountTypeDto toBankAccountTypeDtoDto(BankAccountType bankAccountType) {
        BankAccountTypeDto dto = new BankAccountTypeDto();
        dto.setName(bankAccountType.getName());
        dto.setCode(bankAccountType.getCode());
        dto.setBenefits(bankAccountType.getBenefits());
        return dto;
    }
}
