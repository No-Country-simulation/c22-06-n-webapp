package com.moneda.back.dto;

import com.moneda.back.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private String bankAccount;
    private String cvu;
    private Double balance;
    private String alias;
    private String user_dni;
    private BankAccountTypeDto bankAccountType;
}
