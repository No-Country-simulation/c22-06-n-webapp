package com.moneda.back.services;

import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.dto.CreateBankAccountTypeDto;
import com.moneda.back.dto.UpdateBankAccountTypeDto;
import com.moneda.back.entities.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface BankAccountTypeService {
    public ResponseEntity<Map<String, Object>> listBankAccountTypes();
    public ResponseEntity<Map<String, Object>> saveBankAccountType(CreateBankAccountTypeDto createBankAccountTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateBankAccountType(Integer id, UpdateBankAccountTypeDto updateBankAccountTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> getBankAccountTypeById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteBankAccountType(Integer id);
}
