package com.moneda.back.dto;

import lombok.Data;

@Data
public class CreateCurrencyDto {
    private String name;
    private String code;
    private String symbol;
}
