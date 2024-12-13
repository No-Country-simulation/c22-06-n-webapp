package com.moneda.back.services;


import com.moneda.back.dto.CreateBankAccountDto;
import com.moneda.back.dto.UpdateBankAccountDto;
import com.moneda.back.entities.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface BankAccountService {
    public ResponseEntity<Map<String, Object>> listBankAccounts();
    public ResponseEntity<Map<String, Object>> saveBankAccount(CreateBankAccountDto createBankAccount, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateBankAccount(Integer id, UpdateBankAccountDto updateBankAccount, BindingResult result);
    public ResponseEntity<Map<String, Object>> getBankAccountById(Integer id);
    public ResponseEntity<Map<String, Object>> getBankAccountByCode(String code);
    public ResponseEntity<Map<String, Object>> deleteBankAccount(Integer id);
}
