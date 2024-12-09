package com.moneda.back.repositories;

import com.moneda.back.entities.UserBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBankAccountRepository extends JpaRepository<UserBankAccount, Integer> {
}
