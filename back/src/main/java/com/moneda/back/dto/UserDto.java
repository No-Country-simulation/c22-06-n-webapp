package com.moneda.back.dto;

import com.moneda.back.entities.BankAccount;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String firstName;
    private String lastName_p;
    private String lastName_m;
    private String dni;
    private String address;
    private Date birthDate;
    private String cuil;
    private String email;
    private String phone;
    private String photo_url;
    private BankAccountDto bankAccount;
}
