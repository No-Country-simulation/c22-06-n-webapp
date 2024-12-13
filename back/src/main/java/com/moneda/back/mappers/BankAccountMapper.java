package com.moneda.back.mappers;
import com.moneda.back.dto.BankAccountDto;
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
        dto.setUser_id(bankAccount.getUser().getId());
        dto.setCvu(bankAccount.getCvu());
        dto.setBankAccountType(bankAccountTypeMapper.toBankAccountTypeDto(bankAccount.getBankAccountType()));
        dto.setBalance(bankAccount.getBalance());
        dto.setAlias(bankAccount.getAlias());
        return dto;
    }
}
