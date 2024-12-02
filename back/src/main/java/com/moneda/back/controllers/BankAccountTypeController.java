package com.moneda.back.controllers;
import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.entities.BankAccountType;
import com.moneda.back.services.BankAccountTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/tipo_cuenta_banco")
@AllArgsConstructor
public class BankAccountTypeController {
    private final BankAccountTypeService bankAccountTypeService;

    @RequestMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listBankAccountTypes(){
        return bankAccountTypeService.listBankAccountTypes();
    }
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCurrency(@RequestBody BankAccountTypeDto createBankAccountTypeDto, BindingResult result){
        return bankAccountTypeService.saveBankAccountType(createBankAccountTypeDto, result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCurrency(@PathVariable Integer id, @RequestBody BankAccountTypeDto updateBankAccountTypeDto, BindingResult result){
        return bankAccountTypeService.updateBankAccountType(id, updateBankAccountTypeDto, result);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCurrency(@PathVariable Integer id){
        return bankAccountTypeService.deleteBankAccountType(id);
    }
    @RequestMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getCurrencyById(@PathVariable Integer id){
        return bankAccountTypeService.getBankAccountTypeById(id);
    }
}
