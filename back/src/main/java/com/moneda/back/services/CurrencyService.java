package com.moneda.back.services;

import com.moneda.back.dto.CurrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface CurrencyService {
    public ResponseEntity<Map<String, Object>> getCurrencies();
    public ResponseEntity<Map<String, Object>> saveCurrency(CurrencyDto currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateCurrency(Integer id, CurrencyDto currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> getCurrencyById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteCurrency(Integer id);
}
