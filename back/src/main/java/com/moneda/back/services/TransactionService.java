package com.moneda.back.services;

import com.moneda.back.dto.CreateTransactionDto;
import com.moneda.back.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface TransactionService {
    public ResponseEntity<Map<String, Object>> saveTransaction(CreateTransactionDto createTransactionDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> saveDeposit(CreateTransactionDto createTransactionDto, BindingResult result);
}
