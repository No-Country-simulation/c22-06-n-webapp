package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "historial_transaccion")
@Data
@Entity
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_transaccion")
    private Integer id;
    @Column(name = "cvu_usuario")
    private String cvuUser;
    @Column(name = "cvu_destino", nullable = false)
    private String transactionType;
    @Column(name = "monto", nullable = false)
    private Double amount;
    @Column(name = "detalle", nullable = false)
    private String details;
    @Column(name = "fecha_transaccion", nullable = false)
    private Date transactionDate;
}
