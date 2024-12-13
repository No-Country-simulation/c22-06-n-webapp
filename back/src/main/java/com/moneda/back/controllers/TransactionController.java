package com.moneda.back.controllers;
import com.moneda.back.dto.CreateTransactionDepositDto;
import com.moneda.back.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
@RequestMapping("/api/transaccion")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @Operation(summary = "Deposito a cuenta por cvu")
    @PostMapping("/deposit")
    public ResponseEntity<Map<String, Object>> saveDeposit(CreateTransactionDepositDto createTransactionDepositDto, BindingResult result){
        return transactionService.saveDeposit(createTransactionDepositDto, result);
    }
}
