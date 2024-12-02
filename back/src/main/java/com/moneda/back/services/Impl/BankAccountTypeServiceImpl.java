package com.moneda.back.services.Impl;

import com.moneda.back.dto.BankAccountTypeDto;
import com.moneda.back.entities.BankAccountType;
import com.moneda.back.mappers.BankAccountTypeMapper;
import com.moneda.back.repositories.BankAccountTypeRepository;
import com.moneda.back.services.BankAccountTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class  BankAccountTypeServiceImpl implements BankAccountTypeService {
    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final BankAccountTypeMapper bankAccountTypeMapper;
    @Override
    public ResponseEntity<Map<String, Object>> listBankAccountTypes() {
        Map<String, Object> response = new HashMap<>();
        List<BankAccountTypeDto> bankAccountTypes = bankAccountTypeRepository.findAll()
                .stream()
                .map(bankAccountTypeMapper::toBankAccountTypeDtoDto)
                .collect(Collectors.toList());
        if(bankAccountTypes.isEmpty()){
            response.put("message", "No hay datos");
            response.put("bankAccountTypes", Collections.emptyList());
        }else {
            response.put("message", "Listas Tipo Cuentas de Banco");
            response.put("bankAccountTypes", bankAccountTypes);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveBankAccountType(BankAccountTypeDto createBankAccountTypeDto, BindingResult result) {
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
            BankAccountType bankAccountType = new BankAccountType();
            bankAccountType.setName(createBankAccountTypeDto.getName());
            bankAccountType.setCode(createBankAccountTypeDto.getCode());
            bankAccountType.setBenefits(createBankAccountTypeDto.getBenefits());

            bankAccountType.setIsActive(true); //por defecto estará activo al crear
            bankAccountTypeRepository.save(bankAccountType);

            BankAccountTypeDto bankAccountTypeDto = new BankAccountTypeDto();
            bankAccountTypeDto.setCode(bankAccountType.getCode());
            bankAccountTypeDto.setName(bankAccountType.getName());
            bankAccountTypeDto.setBenefits(bankAccountType.getBenefits());
            response.put("message", "Se ha creado exitosamente");
            response.put("bankAccountType", bankAccountTypeDto);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("message", "Error al guardar el Tipo de Cuenta banco");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBankAccountType(Integer id, BankAccountTypeDto updateBankAccountTypeDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<BankAccountType> existingBankAccountType = bankAccountTypeRepository.findById(id);
        if (existingBankAccountType.isEmpty()) {
            response.put("message", "El Tipo Cuenta Banco con ID '" + id + "' no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {
            BankAccountType bankAccountType = existingBankAccountType.get();
            bankAccountType.setName(updateBankAccountTypeDto.getName());
            bankAccountType.setCode(updateBankAccountTypeDto.getCode());
            bankAccountType.setBenefits(updateBankAccountTypeDto.getBenefits());
            BankAccountType updateBankAccountType = bankAccountTypeRepository.save(bankAccountType);

            BankAccountTypeDto dto = new BankAccountTypeDto();
            dto.setCode(bankAccountType.getCode());
            dto.setName(bankAccountType.getName());
            dto.setBenefits(bankAccountType.getBenefits());
            response.put("message", "Tipo Cuenta Banco actualizado correctamente");
            response.put("bankAccountType", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el Tipo de Cuenta Banco");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountTypeById(Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<BankAccountType> bankAccountTypeOptional = bankAccountTypeRepository.findById(id);
            if (bankAccountTypeOptional.isEmpty()) {
                response.put("message", "El Tipo Cuenta Banco no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Tipo Cuenta Banco no encontrado");
            response.put("bankAccountType", bankAccountTypeOptional.get());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error al buscar el Tipo Cuenta Banco");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBankAccountType(Integer id) {
        Map<String, Object> response = new HashMap<>();
        if(!bankAccountTypeRepository.existsById(id)){
            response.put("message", "El Tipo Cuenta Banco no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            Optional<BankAccountType> existingBankAccountType = bankAccountTypeRepository.findById(id);
            if (existingBankAccountType.isPresent()) {
                BankAccountType bankAccountTypeEntity = existingBankAccountType.get();
                bankAccountTypeEntity.setIsActive(false);
                bankAccountTypeRepository.save(bankAccountTypeEntity);
                response.put("message", "El Tipo Cuenta Banco se eliminó exitosamente");
                response.put("bankAccountType", bankAccountTypeEntity);
                return ResponseEntity.ok(response);
            }
        }
        catch (Exception e){
            response.put("message", "Error al eliminar el Tipo Cuena Banco");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "Error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
