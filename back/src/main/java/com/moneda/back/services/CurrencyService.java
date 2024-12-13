package com.moneda.back.services;

import com.moneda.back.dto.CreateCurrencyDto;
import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.dto.UpdateCurrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface CurrencyService {
    public ResponseEntity<Map<String, Object>> getCurrencies();
    public ResponseEntity<Map<String, Object>> saveCurrency(CreateCurrencyDto currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateCurrency(Integer id, UpdateCurrencyDto currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> getCurrencyById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteCurrency(Integer id);
}
