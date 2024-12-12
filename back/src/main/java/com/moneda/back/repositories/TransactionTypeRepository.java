package com.moneda.back.repositories;

import com.moneda.back.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
    List<TransactionType> findByIsActiveTrue();
}
