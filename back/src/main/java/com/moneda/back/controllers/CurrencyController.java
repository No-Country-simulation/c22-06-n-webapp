package com.moneda.back.controllers;

import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.services.CurrencyService;
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

    @RequestMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> getCurrencies(){
        return currencyService.getCurrencies();
    }
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCurrency(@RequestBody CurrencyDto createCurrencyDto, BindingResult result){
        return currencyService.saveCurrency(createCurrencyDto, result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCurrency(@PathVariable Integer id, @RequestBody CurrencyDto currencyDto, BindingResult result){
        return currencyService.updateCurrency(id, currencyDto, result);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCurrency(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }
    @RequestMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }
}
