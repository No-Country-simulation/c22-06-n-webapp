package com.moneda.back.controllers;
import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.entities.BankAccountType;
import com.moneda.back.services.BankAccountTypeService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Obtiene todos los Tipos de Cuenta Banco registrados")
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listBankAccountTypes(){
        return bankAccountTypeService.listBankAccountTypes();
    }
    @Operation(summary = "Registra un nuevo Tipo Cuenta Banco")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCurrency(@RequestBody BankAccountTypeDto createBankAccountTypeDto, BindingResult result){
        return bankAccountTypeService.saveBankAccountType(createBankAccountTypeDto, result);
    }
    @Operation(summary = "Actualiza un Tipo Cuenta Banco por Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCurrency(@PathVariable Integer id, @RequestBody BankAccountTypeDto updateBankAccountTypeDto, BindingResult result){
        return bankAccountTypeService.updateBankAccountType(id, updateBankAccountTypeDto, result);
    }
    @Operation(summary = "Elimina un Tipo Cuenta Banco por Id (Eliminación Logica)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCurrency(@PathVariable Integer id){
        return bankAccountTypeService.deleteBankAccountType(id);
    }
    @Operation(summary = "Obtiene un Tipo Cuenta Banco por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getCurrencyById(@PathVariable Integer id){
        return bankAccountTypeService.getBankAccountTypeById(id);
    }
}
