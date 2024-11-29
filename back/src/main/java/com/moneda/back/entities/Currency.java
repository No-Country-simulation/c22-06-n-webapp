package com.moneda.back.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "moneda")
@Entity
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_moneda")
    private Integer id;
    @Column(name = "nombre", length = 100, nullable = false)
    private String name;
    @Column(name = "codigo", length = 100, nullable = false)
    private String code;
    @Column(name = "activo")
    private Boolean isActive;
}
