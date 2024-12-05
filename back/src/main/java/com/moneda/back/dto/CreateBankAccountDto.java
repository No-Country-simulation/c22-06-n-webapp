package com.moneda.back.dto;

import com.moneda.back.entities.BankAccountType;
import lombok.Data;

import java.util.Date;

@Data
public class CreateBankAccountDto{
    private String bankAccount;
    private String cvu;
    private Double balance;
    private String alias;
    private Integer user_id;
    private Integer bankAccountType_id;
}
