package com.moneda.back.services;

import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.entities.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface BankAccountTypeService {
    public ResponseEntity<Map<String, Object>> listBankAccountTypes();
    public ResponseEntity<Map<String, Object>> saveBankAccountType(BankAccountTypeDto createBankAccountTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateBankAccountType(Integer id, BankAccountTypeDto updateBankAccountTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> getBankAccountTypeById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteBankAccountType(Integer id);
}
