package com.moneda.back.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TransactionHistoryService {
    public ResponseEntity<Map<String, Object>> getTransactionHistoryByCvu(String cvuUser);
}
