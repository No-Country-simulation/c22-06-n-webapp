package com.moneda.back.dto;

import lombok.Data;

@Data
public class CreateTransactionTypeDto {
    private String code;
    private String name;
}
