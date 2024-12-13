package com.moneda.back.controllers;

import com.moneda.back.dto.BankAccountDto;
import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.dto.CreateBankAccountDto;
import com.moneda.back.dto.UpdateBankAccountDto;
import com.moneda.back.services.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/cuenta_banco")
@AllArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;
    @Operation(summary = "Obtiene todos las Cuentas de Banco registradas")
    @ApiResponse(responseCode = "200", description = "Listado de cuentas de banco",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BankAccountDto.class)))
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listBankAccounts(){
        return bankAccountService.listBankAccounts();
    }
    @Operation(summary = "Registra una nueva Cuenta Banco")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveBankAccount(@RequestBody CreateBankAccountDto createBankAccountDto, BindingResult result){
        return bankAccountService.saveBankAccount(createBankAccountDto, result);
    }
    @Operation(summary = "Actualiza una Cuenta Banco por Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateBankAccount(@PathVariable Integer id, @RequestBody UpdateBankAccountDto updateBankAccountDto, BindingResult result){
        return bankAccountService.updateBankAccount(id, updateBankAccountDto, result);
    }
    @Operation(summary = "Elimina una Cuenta Banco por Id (Eliminación Logica)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBankAccount(@PathVariable Integer id){
        return bankAccountService.deleteBankAccount(id);
    }
    @Operation(summary = "Obtiene una Cuenta Banco por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getBankAccountById(@PathVariable Integer id){
        return bankAccountService.getBankAccountById(id);
    }
}
