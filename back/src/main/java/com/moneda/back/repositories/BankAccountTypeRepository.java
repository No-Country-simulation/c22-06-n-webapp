package com.moneda.back.repositories;

import com.moneda.back.entities.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Integer> {
}
