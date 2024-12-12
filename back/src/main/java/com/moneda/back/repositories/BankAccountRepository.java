package com.moneda.back.repositories;

import com.moneda.back.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    boolean existsByBankAccount(String bankAccount);
    boolean existsByAlias(String alias);
    List<BankAccount> findByIsActiveTrue();
    Optional<BankAccount> findByCvu(String cvu);
}
