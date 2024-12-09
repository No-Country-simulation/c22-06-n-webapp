package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserBankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_banco")
    private BankAccount bankAccount;
}
