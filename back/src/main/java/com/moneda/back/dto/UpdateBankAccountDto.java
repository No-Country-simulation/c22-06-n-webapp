package com.moneda.back.dto;

import lombok.Data;

@Data
public class UpdateBankAccountDto {
    private String bankAccount;
    private String cvu;
    private Double balance;
    private String alias;
    private Integer user_id;
    private Integer bankAccountType_id;
}
