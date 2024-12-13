package com.moneda.back.services;

import com.moneda.back.dto.CreateTransactionDepositDto;
import com.moneda.back.dto.CreateTransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.Map;

public interface TransactionService {
    public ResponseEntity<Map<String, Object>> saveTransaction(CreateTransactionDto createTransactionDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> saveDeposit(CreateTransactionDepositDto createTransactionDepositDto, BindingResult result);
}
