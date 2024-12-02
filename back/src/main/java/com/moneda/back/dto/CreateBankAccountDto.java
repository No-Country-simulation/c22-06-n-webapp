package com.moneda.back.dto;

import com.moneda.back.entities.BankAccountType;
import lombok.Data;
@Data
public class CreateBankAccountDto{
    private String bankAccount;
    private String cvu;
    private Double balance;
    private String alias;
    private BankAccountType bankAccountType;
}
