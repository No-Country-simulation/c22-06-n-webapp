package com.moneda.back.services.Impl;

import com.moneda.back.entities.User;
import com.moneda.back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<Map<String, Object>> searchUsers() {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getUserById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteUser(Integer id) {
        return null;
    }
}
