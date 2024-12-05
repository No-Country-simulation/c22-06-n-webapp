package com.moneda.back.dto;

import lombok.Data;

import java.util.Date;
@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName_p;
    private String lastName_m;
    private String address;
    private Date birthDate;
    private String email;
    private String phone;
    private String photo_url;
}
