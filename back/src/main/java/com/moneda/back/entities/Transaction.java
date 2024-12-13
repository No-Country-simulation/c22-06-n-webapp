package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "transaccion")
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer id;
    @Column(name = "cvu_remitente")
    private String cvuSender;
    @Column(name = "cvu_destino", nullable = false)
    private String cvuReceiver;
    @Column(name = "monto", nullable = false)
    private BigDecimal amount;
    @Column(name = "fecha_transferencia", nullable = false)
    private Date transactionDate;
    @Column(name = "detalle_transaccion", nullable = false)
    private String details;
    @Column(name = "mensaje")
    private String message;
    @Column(name = "creado_en", nullable = false)
    private Date createdAt;
    @Column(name = "activo", nullable = false)
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

}
