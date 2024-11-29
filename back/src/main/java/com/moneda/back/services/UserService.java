package com.moneda.back.services;

import com.moneda.back.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public ResponseEntity<Map<String, Object>> searchUsers();
    public ResponseEntity<Map<String, Object>> saveUser(User user);
    public ResponseEntity<Map<String, Object>> getUserById(Integer id);
    public ResponseEntity<Map<String, Object>> deleteUser(Integer id);
}
