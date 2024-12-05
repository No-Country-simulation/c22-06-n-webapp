package com.moneda.back.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private List<BankAccountDto> bankAccounts;

}
