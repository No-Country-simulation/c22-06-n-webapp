package com.moneda.back.repositories;

import com.moneda.back.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    boolean existsByBankAccount(String bankAccount);
    boolean existsByAlias(String alias);
}
