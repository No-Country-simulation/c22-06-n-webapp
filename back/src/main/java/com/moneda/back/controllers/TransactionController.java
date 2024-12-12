package com.moneda.back.controllers;

import com.moneda.back.dto.CreateTransactionDepositDto;
import com.moneda.back.dto.CreateUserDto;
import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.dto.TransactionDto;
import com.moneda.back.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/api/transaccion")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @Operation(summary = "Deposito a cuenta por cvu")
    @ApiResponse(responseCode = "200", description = "Detalle de la transacción",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransactionDto.class)))
    @PostMapping("/deposit")
    public ResponseEntity<Map<String, Object>> saveDeposit(CreateTransactionDepositDto createTransactionDepositDto, BindingResult result){
        return transactionService.saveDeposit(createTransactionDepositDto, result);
    }
}
