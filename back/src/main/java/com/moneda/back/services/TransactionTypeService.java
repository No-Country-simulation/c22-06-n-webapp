package com.moneda.back.services;

import com.moneda.back.dto.CreateTransactionTypeDto;
import com.moneda.back.dto.TransactionTypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface TransactionTypeService {
    public ResponseEntity<Map<String, Object>> listTransactionTypes();
    public ResponseEntity<Map<String, Object>> saveTransactionType(CreateTransactionTypeDto createTransactionTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateTransactionType(Integer id, TransactionTypeDto updateTransactionTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> getTransactionTypeById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteTransactionType(Integer id);
}
