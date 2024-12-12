package com.moneda.back.services.Impl;
import com.moneda.back.dto.CreateTransactionDepositDto;
import com.moneda.back.dto.CreateTransactionDto;
import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.BankAccount;
import com.moneda.back.entities.Transaction;
import com.moneda.back.entities.TransactionHistory;
import com.moneda.back.entities.TransactionType;
import com.moneda.back.mappers.DepositHistoryMapper;
import com.moneda.back.repositories.BankAccountRepository;
import com.moneda.back.repositories.TransactionRepository;
import com.moneda.back.repositories.TransactionTypeRepository;
import com.moneda.back.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final DepositHistoryMapper depositHistoryMapper;
    @Override
    public ResponseEntity<Map<String, Object>> saveTransaction(CreateTransactionDto createTransactionDto, BindingResult result) {
        return null;
    }
    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> saveDeposit(CreateTransactionDepositDto createTransactionDepositDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
      try{
          BankAccount bankAccountReceiver = bankAccountRepository.findByCvu(createTransactionDepositDto.getCvuReceiver())
                  .orElseThrow(()-> new IllegalArgumentException("Cuenta destino no encontrada"));

          bankAccountReceiver.setBalance(bankAccountReceiver.getBalance().add(createTransactionDepositDto.getAmount()));
          bankAccountRepository.save(bankAccountReceiver);
          Transaction transaction = new Transaction();
          transaction.setCvuSender(null);
          transaction.setCvuReceiver(createTransactionDepositDto.getCvuReceiver());
          transaction.setAmount(createTransactionDepositDto.getAmount());
          transaction.setDetails("Deposito a cuenta");
          transaction.setMessage(createTransactionDepositDto.getMessage());
          transaction.setTransactionDate(new Date());
          transaction.setCreatedAt(new Date());
          transaction.setIsActive(true);
          TransactionType depositType = transactionTypeRepository.findByCode("02-Deposito")
                  .orElseThrow(() -> new RuntimeException("Tipo de transacción no encontrado"));
          transaction.setTransactionType(depositType);
          transactionRepository.save(transaction);
          TransactionHistoryDto historyDto = depositHistoryMapper.createHistoryDto(transaction);
          response.put("message", "Depósito realizado exitosamente");
          response.put("transaction", transaction);
          return ResponseEntity.ok(response);
      }catch (Exception e) {
          response.put("message", "Error al realizar el depósito");
          response.put("error", e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    }
}
