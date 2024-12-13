package com.moneda.back.services.Impl;

import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.TransactionHistory;
import com.moneda.back.mappers.TransactionHistoryMapper;
import com.moneda.back.repositories.TransactionHistoryRepository;
import com.moneda.back.services.TransactionHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final TransactionHistoryMapper transactionHistoryMapper;
    @Override
    public ResponseEntity<Map<String, Object>> getTransactionHistoryByCvu(String cvuUser) {
        Map<String, Object> response = new HashMap<>();
        List<TransactionHistory> histories = transactionHistoryRepository.findByCvuUser(cvuUser);
        if (histories.isEmpty()) {
            response.put("message", "No se encontraron movimientos para el CVU: " + cvuUser);
            response.put("histories", Collections.emptyList());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        List<TransactionHistoryDto> historyDtos = transactionHistoryMapper.toTransactionHistoryDto(histories);

        response.put("message", "Historial encontrado exitosamente");
        response.put("histories", historyDtos);
        return ResponseEntity.ok(response);
    }
}
