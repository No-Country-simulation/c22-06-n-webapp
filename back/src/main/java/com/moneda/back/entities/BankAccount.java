package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "cuenta_banco")
@Data
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta_banco", nullable = false)
    private Integer id;
    @Column(name = "cuenta_banco", nullable = false, columnDefinition = "CHAR(10)")
    private String bankAccount;
    @Column(name = "cvu", nullable = false, columnDefinition = "CHAR(22)")
    private String cvu;
    @Column(name = "saldo", nullable = false)
    private Double balance;
    @Column(name = "alias", nullable = false, columnDefinition = "CHAR(4)")
    private String alias;
    @Column(name = "creado_en")
    private Date createdAt;
    @Column(name = "ultima_actualizacion")
    private Date lastModified;
    @Column(name = "activo")
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta_banco", nullable = false)
    private BankAccountType bankAccountType;
}
