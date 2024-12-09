package com.moneda.back.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankAccountTypeDto {
    private String name;
    private String code;
    private Integer currency_id;
}
