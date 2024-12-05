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
        dto.setCvu(bankAccount.getBankAccount());
        dto.setBankAccountType(bankAccountTypeMapper.toBankAccountTypeDto(bankAccount.getBankAccountType()));
        dto.setBalance(bankAccount.getBalance());
        dto.setAlias(bankAccount.getAlias());
        return dto;
    }
}
