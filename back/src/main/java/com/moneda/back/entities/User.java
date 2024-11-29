package com.moneda.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "usuarios")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;
    @Column(name = "nombre", length = 50, nullable = false)
    private String name;
    @Column(name = "apellido", length = 50, nullable = false)
    private String lastName;
    @Column(name = "dni", columnDefinition = "CHAR(8)", nullable = false)
    private String dni;
    @Column(name = "direccion")
    private String address;
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date dateOfBirth;
    @Column(name = "cuil", columnDefinition = "CHAR(11)", nullable = false)
    private String cuil;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", columnDefinition = "CHAR(6)", nullable = false)
    private String password;
    @Column(name = "telefono", columnDefinition = "CHAR(11)")
    private String phone;
    @Column(name = "foto_perfil")
    private String photo;
}

