package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tipo_cuenta_banco")
@Data
@Entity
public class BankAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cuenta_banco")
    private Integer id;
    @Column(name = "nombre", length = 250, nullable = false)
    private String name;
    @Column(name = "beneficios", length = 250, nullable = false)
    private String benefits;
    @Column(name = "codigo", length = 250, nullable = false)
    private String code;
    @Column(name = "activo")
    private Boolean isActive;
}
