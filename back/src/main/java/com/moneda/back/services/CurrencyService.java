package com.moneda.back.services;

import com.moneda.back.entities.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface CurrencyService {
    public ResponseEntity<Map<String, Object>> getCurrencies();
    public ResponseEntity<Map<String, Object>> saveCurrency(Currency currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateCurrency(Integer id, Currency currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> getCurrencyById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteCurrency(Integer id);
}
