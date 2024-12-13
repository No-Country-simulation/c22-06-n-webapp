package com.moneda.back.repositories;

import com.moneda.back.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
    List<TransactionType> findByIsActiveTrue();
    Optional<TransactionType> findByCode(String code);
}
