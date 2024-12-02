package com.moneda.back.repositories;

import com.moneda.back.entities.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Integer> {
}
