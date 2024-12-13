package com.moneda.back.controllers;
import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.services.TransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/api/historial")
@AllArgsConstructor
public class TransactionHistoryController {
    private final TransactionHistoryService transactionHistoryService;
    @Operation(summary = "Busca los movimientos de un usuario por cvu")
    @ApiResponse(responseCode = "200", description = "Lista de movimientos del usuario",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransactionHistoryDto.class)))
    @GetMapping("/user/{cvu}")
    public ResponseEntity<Map<String, Object>> getTransactionHistoryByCvu(@PathVariable String cvuUser){
        return transactionHistoryService.getTransactionHistoryByCvu(cvuUser);
    }
}
