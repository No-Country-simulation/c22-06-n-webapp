package com.moneda.back.dto;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName_p;
    private String lastName_m;
    private Integer user_id;
    private String address;
    private Date birthDate;
    private String cuil;
    private String email;
    private String phone;
    private String photo_url;
    private List<BankAccountDto> bankAccounts;

}
