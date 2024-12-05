package com.moneda.back.mappers;

import com.moneda.back.dto.BankAccountDto;
import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.entities.BankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountMapper {
    private final BankAccountTypeMapper bankAccountTypeMapper;
    public BankAccountDto toBankAccountDto(BankAccount bankAccount) {
        BankAccountDto dto = new BankAccountDto();
        dto.setBankAccount(bankAccount.getBankAccount());
        dto.setBankAccountType(bankAccountTypeMapper.toBankAccountTypeDto(bankAccount.getBankAccountType()));
        dto.setAlias(bankAccount.getAlias());
        dto.setCvu(bankAccount.getCvu());
        dto.setBalance(bankAccount.getBalance());
        return dto;
    }
}
