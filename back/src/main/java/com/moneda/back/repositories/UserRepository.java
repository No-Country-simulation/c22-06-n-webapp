package com.moneda.back.repositories;

import com.moneda.back.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByIsActiveTrue();
}
