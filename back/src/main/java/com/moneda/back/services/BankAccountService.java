package com.moneda.back.services;


import com.moneda.back.entities.BankAccount;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BankAccountService {
    public ResponseEntity<Map<String, Object>> listBankAccounts();
    public ResponseEntity<Map<String, Object>> createBankAccount(BankAccount bankAccount);
    public ResponseEntity<Map<String, Object>> updateBankAccount(BankAccount bankAccount);
    public ResponseEntity<Map<String, Object>> findBankAccountById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteBankAccount(Integer id);
}
