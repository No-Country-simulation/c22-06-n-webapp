package com.moneda.back.services;

import com.moneda.back.dto.CreateUserDto;
import com.moneda.back.dto.UpdateUserDto;
import com.moneda.back.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface UserService {
    public ResponseEntity<Map<String, Object>> listUsers();
    public ResponseEntity<Map<String, Object>> saveUser(CreateUserDto createUserDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateUser(Integer id, UpdateUserDto updateUserDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> getUserById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteUser(Integer id);
    public ResponseEntity<Map<String, Object>> getUserByBankAccount(String bankAccount);
}
