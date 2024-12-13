package com.moneda.back.controllers;
import com.moneda.back.dto.CreateTransactionTypeDto;
import com.moneda.back.services.TransactionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@Controller
@RequestMapping("/api/tipo_transaccion")
@AllArgsConstructor
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;
    @Operation(summary = "Registrar Tipo de Transacción")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveTransactionType(CreateTransactionTypeDto createTransactionTypeDto, BindingResult result){
        return transactionTypeService.saveTransactionType(createTransactionTypeDto, result);
    }
}
