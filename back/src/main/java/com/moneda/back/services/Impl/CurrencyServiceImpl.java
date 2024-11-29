package com.moneda.back.services.Impl;

import com.moneda.back.entities.Currency;
import com.moneda.back.repositories.CurrencyRepository;
import com.moneda.back.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    public ResponseEntity<Map<String, Object>> getCurrencies() {
        Map<String, Object> response = new HashMap<>();
        List<Currency> currencies = currencyRepository.findAll();
        if(currencies.isEmpty()){
        response.put("message", "No hay datos");
        response.put("currencies", Collections.emptyList());
        }else {
            response.put("message", "Listas Tipo Moneda");
            response.put("currencies", currencies);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveCurrency(Currency currency, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
        try{
            Currency currencyData = currencyRepository.save(currency);
            currencyData.setIsActive(true);
            response.put("message", "Se ha creado exitosamente");
            response.put("currency", currencyData);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("message", "Error al guardar la moneda");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateCurrency(Integer id, Currency currency, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
        Optional<Currency> existingCurrency = currencyRepository.findById(id);
        if (existingCurrency.isEmpty()) {
            response.put("message", "La moneda con ID '" + id + "' no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {
            Currency currencyEntity = existingCurrency.get();
            currencyEntity.setName(currency.getName());
            currencyEntity.setCode(currency.getCode());

            Currency updatedCurrency = currencyRepository.save(currencyEntity);
            response.put("message", "Moneda actualizada correctamente");
            response.put("currency", updatedCurrency);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al actualizar la moneda");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getCurrencyById(Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Currency> currencyOptional = currencyRepository.findById(id);
            if (currencyOptional.isEmpty()) {
                response.put("message", "La moneda no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Moneda encontrada");
            response.put("currency", currencyOptional.get());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error al buscar la moneda");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteCurrency(Integer id) {
        Map<String, Object> response = new HashMap<>();
        if(!currencyRepository.existsById(id)){
            response.put("message", "La moneda no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            Optional<Currency> existingCurrency = currencyRepository.findById(id);
            if (existingCurrency.isPresent()) {
                Currency currencyEntity = existingCurrency.get();
                currencyEntity.setIsActive(false);
                currencyRepository.save(currencyEntity);
                response.put("message", "La moneda se eliminó exitosamente");
                response.put("currency", currencyEntity);
                return ResponseEntity.ok(response);
            }
        }
        catch (Exception e){
                response.put("message", "Error al eliminar la moneda");
                response.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        response.put("message", "Error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}
