package com.moneda.back.dto;

import com.moneda.back.entities.BankAccountType;
import lombok.Data;

import java.util.Date;

@Data
public class CreateBankAccountDto{
    private Integer user_id;
    private Integer bankAccountType_id;
}
