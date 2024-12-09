package com.moneda.back.controllers;

import com.moneda.back.dto.BankAccountDto;
import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.services.CurrencyService;
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
@RequestMapping("/api/moneda")
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;
    @Operation(summary = "Obtiene todos los tipos de moneda registrados")
    @ApiResponse(responseCode = "200", description = "Listado de monedas",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyDto.class)))
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> getCurrencies(){
        return currencyService.getCurrencies();
    }
    @Operation(summary = "Registrar un Tipo Moneda")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCurrency(@RequestBody CurrencyDto createCurrencyDto, BindingResult result){
        return currencyService.saveCurrency(createCurrencyDto, result);
    }
    @Operation(summary = "Actualizar Tipo Moneda por Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCurrency(@PathVariable Integer id, @RequestBody CurrencyDto currencyDto, BindingResult result){
        return currencyService.updateCurrency(id, currencyDto, result);
    }
    @Operation(summary = "Eliminación Logica de Tipo Moneda por Id, cambia el estado active a false")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCurrency(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }
    @Operation(summary = "Obtiene el nombre y codigo del id tipo de moneda")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }
}
