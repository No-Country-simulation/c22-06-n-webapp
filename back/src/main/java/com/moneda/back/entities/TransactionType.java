package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "tipo_transaccion")
@Data
@Entity
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_transaccion")
    private Integer id;
    @Column(name = "codigo", length = 250, nullable = false)
    private String code;
    @Column(name = "nombre", length = 250, nullable = false)
    private String name;
    @Column(name = "creado_en")
    private Date createdAt;
    @Column(name = "ultima_actualizacion")
    private Date lastModified;
    @Column(name = "activo")
    private Boolean isActive;
}
