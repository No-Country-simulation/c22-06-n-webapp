package com.moneda.back.dto;

import lombok.Data;

@Data
public class BankAccountDto {
    private String bankAccount;
    private String cvu;
    private Double balance;
    private String alias;
    private UserDto user;
    private BankAccountTypeDto bankAccountType;
}
