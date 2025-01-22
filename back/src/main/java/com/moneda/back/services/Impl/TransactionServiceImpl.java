package com.moneda.back.services.Impl;
import com.moneda.back.dto.CreateTransactionDepositDto;
import com.moneda.back.dto.CreateTransactionDto;
import com.moneda.back.dto.TransactionHistoryDto;
import com.moneda.back.entities.BankAccount;
import com.moneda.back.entities.Transaction;
import com.moneda.back.entities.TransactionHistory;
import com.moneda.back.entities.TransactionType;
import com.moneda.back.mappers.DepositHistoryMapper;
import com.moneda.back.mappers.TransferHistoryMapper;
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
    private final TransferHistoryMapper transferHistoryMapper;
    private final DepositHistoryMapper depositHistoryMapper;
    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> saveTransaction(CreateTransactionDto createTransactionDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            BankAccount bankAccountSender = bankAccountRepository.findByCvu(createTransactionDto.getCvuSender())
                    .orElseThrow(() -> new IllegalArgumentException("Cuenta emisora no encontrada"));

            BankAccount bankAccountReceiver = bankAccountRepository.findByCvu(createTransactionDto.getCvuReceiver())
                    .orElseThrow(() -> new IllegalArgumentException("Cuenta receptora no encontrada"));

            // Validar saldo disponible
            if (bankAccountSender.getBalance().compareTo(createTransactionDto.getAmount()) < 0) {
                throw new IllegalArgumentException("Saldo insuficiente en la cuenta emisora");
            }

            // Actualizar saldos
            bankAccountSender.setBalance(bankAccountSender.getBalance().subtract(createTransactionDto.getAmount()));
            bankAccountReceiver.setBalance(bankAccountReceiver.getBalance().add(createTransactionDto.getAmount()));

            bankAccountRepository.save(bankAccountSender);
            bankAccountRepository.save(bankAccountReceiver);

            // Crear transacción
            Transaction transaction = new Transaction();
            transaction.setCvuSender(createTransactionDto.getCvuSender());
            transaction.setCvuReceiver(createTransactionDto.getCvuReceiver());
            transaction.setAmount(createTransactionDto.getAmount());
            transaction.setDetails("Transeferencia bancaria");
            transaction.setMessage(createTransactionDto.getMessage());
            transaction.setTransactionDate(new Date());
            transaction.setCreatedAt(new Date());
            transaction.setIsActive(true);

            TransactionType transferType = transactionTypeRepository.findByCode("01-Transferencia")
                    .orElseThrow(() -> new RuntimeException("Tipo de transacción no encontrado"));
            transaction.setTransactionType(transferType);

            transactionRepository.save(transaction);

            // Crear historial de movimientos
            TransactionHistoryDto senderHistory = transferHistoryMapper.createHistoryDtoForSender(transaction);
            TransactionHistoryDto receiverHistory = transferHistoryMapper.createHistoryDtoForReceiver(transaction);

            response.put("message", "Transferencia realizada exitosamente");
            response.put("transaction", transaction);
            response.put("senderHistory", senderHistory);
            response.put("receiverHistory", receiverHistory);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al realizar la transferencia");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
          TransactionType depositType = transactionTypeRepository.findByCode(" ")
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
