package com.moneda.back.dto;

import lombok.Data;

@Data
public class CurrencyDto {
    private Integer id;
    private String name;
    private String code;
    private String symbol;
}
