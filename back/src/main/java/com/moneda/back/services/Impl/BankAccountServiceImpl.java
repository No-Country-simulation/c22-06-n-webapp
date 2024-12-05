package com.moneda.back.services.Impl;

import com.moneda.back.dto.CreateBankAccountDto;
import com.moneda.back.dto.UpdateBankAccountDto;
import com.moneda.back.repositories.BankAccountTypeRepository;
import com.moneda.back.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountTypeRepository bankAccountTypeRepository;
    @Override
    public ResponseEntity<Map<String, Object>> listBankAccounts() {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveBankAccount(CreateBankAccountDto createBankAccount, BindingResult result) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBankAccount(Integer id, UpdateBankAccountDto updateBankAccount, BindingResult result) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountByCode(String code) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBankAccount(Integer id) {
        return null;
    }
}
