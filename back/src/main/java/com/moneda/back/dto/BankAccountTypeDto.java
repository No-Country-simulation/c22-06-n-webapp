package com.moneda.back.dto;

import com.moneda.back.entities.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountTypeDto {
    private Integer id;
    private String name;
    private String code;
    private CurrencyDto currency;
}
