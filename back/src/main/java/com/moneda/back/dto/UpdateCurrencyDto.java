package com.moneda.back.dto;

import lombok.Data;

@Data
public class UpdateCurrencyDto {
    private String name;
    private String code;
    private String symbol;
}
