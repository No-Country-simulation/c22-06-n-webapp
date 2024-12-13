package com.moneda.back.services.Impl;
import com.moneda.back.dto.CreateTransactionTypeDto;
import com.moneda.back.dto.TransactionTypeDto;
import com.moneda.back.entities.TransactionType;
import com.moneda.back.mappers.TransactionTypeMapper;
import com.moneda.back.repositories.TransactionTypeRepository;
import com.moneda.back.services.TransactionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionTypeServiceImpl implements TransactionTypeService {
    private TransactionTypeRepository transactionTypeRepository;
    private TransactionTypeMapper transactionTypeMapper;
    @Override
    public ResponseEntity<Map<String, Object>> listTransactionTypes() {
        Map<String, Object> response = new HashMap<>();
        List<TransactionTypeDto> transactionTypes = transactionTypeRepository.findByIsActiveTrue()
                .stream()
                .map(transactionTypeMapper::toTransactionTypeDto)
                .collect(Collectors.toList());
        if(transactionTypes.isEmpty()){
            response.put("message", "No hay datos");
            response.put("transactionTypes", Collections.emptyList());
        }else {
            response.put("message", "Listas de Tipos de Transacción");
            response.put("transactionTypes", transactionTypes);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveTransactionType(CreateTransactionTypeDto createTransactionTypeDto, BindingResult result) {
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
            TransactionType transactionType = new TransactionType();
            transactionType.setName(createTransactionTypeDto.getName());
            transactionType.setCode(createTransactionTypeDto.getCode());
            transactionType.setCreatedAt(new Date());
            transactionType.setIsActive(true); //por defecto estará activo al crear
            transactionTypeRepository.save(transactionType);
            TransactionTypeDto transactionTypeDto = transactionTypeMapper.toTransactionTypeDto(transactionType);
            response.put("message", "Se ha creado exitosamente");
            response.put("transactionType", transactionTypeDto);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("message", "Error al guardar el Tipo de Transacción");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateTransactionType(Integer id, TransactionTypeDto updateTransactionTypeDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<TransactionType> existingTransactionType = transactionTypeRepository.findById(id);
        if (existingTransactionType.isEmpty()) {
            response.put("message", "El Tipo de Transacción con ID '" + id + "' no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {
            TransactionType transactionType = existingTransactionType.get();
            transactionType.setName(updateTransactionTypeDto.getName());
            transactionType.setCode(updateTransactionTypeDto.getCode());
            transactionType.setLastModified(new Date());
            TransactionType updateTransactionType = transactionTypeRepository.save(transactionType);
            TransactionTypeDto dto = transactionTypeMapper.toTransactionTypeDto(updateTransactionType);
            response.put("message", "Tipo de Transacción actualizada correctamente");
            response.put("transacionType", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el Tipo de Transacción");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getTransactionTypeById(Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(id);
            if (transactionTypeOptional.isEmpty()) {
                response.put("message", "El Tipo de Transacción no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Tipo de Transacción encontrada");
            response.put("transacionType", transactionTypeOptional.get());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error al buscar el Tipo de Transacción");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTransactionType(Integer id) {
        Map<String, Object> response = new HashMap<>();
        if(!transactionTypeRepository.existsById(id)){
            response.put("message", "El Tipo de Transacción no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            Optional<TransactionType> existingTransactionType = transactionTypeRepository.findById(id);
            if (existingTransactionType.isPresent()) {
                TransactionType transactionTypeEntity = existingTransactionType.get();
                transactionTypeEntity.setIsActive(false);
                transactionTypeRepository.save(transactionTypeEntity);
                response.put("message", "El Tipo de Transacción exitosamente");
                response.put("currency", transactionTypeEntity);
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
