package com.moneda.back.services.Impl;
import com.moneda.back.dto.BankAccountDto;
import com.moneda.back.dto.CreateBankAccountDto;
import com.moneda.back.dto.UpdateBankAccountDto;
import com.moneda.back.entities.BankAccount;
import com.moneda.back.entities.BankAccountType;
import com.moneda.back.entities.User;
import com.moneda.back.mappers.BankAccountMapper;
import com.moneda.back.mappers.BankAccountTypeMapper;
import com.moneda.back.repositories.BankAccountRepository;
import com.moneda.back.repositories.BankAccountTypeRepository;
import com.moneda.back.repositories.UserRepository;
import com.moneda.back.services.BankAccountService;
import com.moneda.back.utils.BankAccountGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import com.moneda.utils.ResponseEntityCustom;
import com.moneda.utils.HttpStatusResponse;
@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final UserRepository userRepository;
    private final BankAccountMapper bankAccountMapper;
    private final BankAccountTypeMapper bankAccountTypeMapper;
    @Override
    public ResponseEntity<Map<String, Object>> listBankAccounts() {
            List<BankAccountDto> bankAccounts = bankAccountRepository.findByIsActiveTrue()
                    .stream()
                    .map(bankAccountMapper::toBankAccountDto)
                    .collect(Collectors.toList());
            return bankAccounts.isEmpty() ?
                    ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode()):
                    ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), bankAccounts, HttpStatusResponse.OK.getCode());
        }

    @Override
    public ResponseEntity<Map<String, Object>> saveBankAccount(CreateBankAccountDto createBankAccount, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors, HttpStatusResponse.BAD_REQUEST.getCode());
        }
        try{
            User user = userRepository.findById(createBankAccount.getUser_id()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            BankAccountType bankAccountType = bankAccountTypeRepository.findById(createBankAccount.getBankAccountType_id()).orElseThrow(()-> new RuntimeException("Tipo Cuenta Banco no encontrado"));
            String accountNumber = BankAccountGenerator.generateAccountNumber();
            String cvu = BankAccountGenerator.generateCvu(accountNumber, "123", "4567");
            String alias = BankAccountGenerator.generateAlias();
            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankAccount(accountNumber);
            if (bankAccountRepository.existsByBankAccount(accountNumber)) {
                ResponseEntityCustom.builderResponse("El número de cuenta ya existe", Collections.emptyList(), HttpStatusResponse.BAD_REQUEST.getCode());
            }
            bankAccount.setCvu(cvu);
            bankAccount.setBalance(BigDecimal.ZERO);
            bankAccount.setAlias(alias);
            if (bankAccountRepository.existsByAlias(alias)) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), null, HttpStatusResponse.BAD_REQUEST.getCode());
            }
            bankAccount.setBankAccountType(bankAccountType);
            bankAccount.setUser(user);
            bankAccount.setIsActive(true);
            bankAccount.setCreatedAt(new Date());
            bankAccountRepository.save(bankAccount);
            user.getBankAccounts().add(bankAccount);
            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(bankAccount);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), bankAccountDto, HttpStatusResponse.OK.getCode());
        }catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), null, HttpStatusResponse.BAD_REQUEST.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBankAccount(Integer id, UpdateBankAccountDto updateBankAccount, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<BankAccount> existingBankAccount = bankAccountRepository.findById(id);
        if (existingBankAccount.isEmpty()) {
            response.put("message", "La Cuenta Banco con ID '" + id + "' no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try {
            User user = userRepository.findById(updateBankAccount.getUser_id()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            BankAccountType bankAccountType = bankAccountTypeRepository.findById(updateBankAccount.getBankAccountType_id()).orElseThrow(()-> new RuntimeException("Tipo Cuenta Banco no encontrado"));

            BankAccount bankAccount = existingBankAccount.get();
            bankAccount.setBankAccount(updateBankAccount.getBankAccount());
            bankAccount.setCvu(updateBankAccount.getCvu());
            bankAccount.setAlias(updateBankAccount.getAlias());
            bankAccount.setBalance(updateBankAccount.getBalance());
            bankAccount.setUser(user);
            bankAccount.setBankAccountType(bankAccountType);
            bankAccount.setLastModified(new Date()); //colocará la fecha actual
            bankAccountRepository.save(bankAccount);

            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(bankAccount);
            response.put("message", "La Cuenta Banco se ha actualizado correctamente");
            response.put("bankAccountType", bankAccountDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al actualizar la Cuenta Banco");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountById(Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
            if (bankAccountOptional.isEmpty()) {
                response.put("message", "La Cuenta Banco no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Cuenta Banco no encontrado");
            response.put("bankAccount", bankAccountOptional.get());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error al buscar la Cuenta Banco");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountByCode(String code) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBankAccount(Integer id) {
        Map<String, Object> response = new HashMap<>();
        if(!bankAccountTypeRepository.existsById(id)){
            response.put("message", "La Cuenta Banco no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            Optional<BankAccount> existingBankAccount = bankAccountRepository.findById(id);
            if (existingBankAccount.isPresent()) {
                BankAccount bankAccountEntity = existingBankAccount.get();
                bankAccountEntity.setIsActive(false);
                bankAccountRepository.save(bankAccountEntity);
                response.put("message", "La Cuenta Banco se eliminó exitosamente");
                response.put("bankAccount", bankAccountEntity);
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
