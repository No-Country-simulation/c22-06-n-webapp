package com.moneda.back.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Table(name = "usuarios")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;
    @Column(name = "nombre", length = 50, nullable = false)
    private String firstName;
    @Column(name = "apellido_paterno", length = 50, nullable = false)
    private String lastName_p;
    @Column(name = "apellido_materno", length = 50, nullable = false)
    private String lastName_m;
    @Column(name = "dni", columnDefinition = "CHAR(8)", nullable = false)
    private String dni;
    @Column(name = "direccion")
    private String address;
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date birthDate;
    @Column(name = "cuil", columnDefinition = "CHAR(11)", nullable = false)
    private String cuil;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", columnDefinition = "CHAR(6)", nullable = false)
    private String password;
    @Column(name = "telefono", columnDefinition = "CHAR(11)")
    private String phone;
    @Column(name = "foto_perfil")
    private String photo_url;
    @Column(name = "creado_en")
    private Date createdAt;
    @Column(name = "ultima_actualizacion")
    private Date lastModified;
    @Column(name = "activo")
    private Boolean isActive;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BankAccount> bankAccounts = new ArrayList<>();

}

