package com.moneda.back.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateBankAccountDto {
    private String bankAccount;
    private String cvu;
    private BigDecimal balance;
    private String alias;
    private Integer user_id;
    private Integer bankAccountType_id;
}
