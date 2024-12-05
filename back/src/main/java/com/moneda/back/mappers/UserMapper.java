package com.moneda.back.mappers;

import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.dto.UserDto;
import com.moneda.back.entities.Currency;
import com.moneda.back.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final BankAccountMapper bankAccountMapper;
    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName_p(user.getLastName_p());
        dto.setLastName_m(user.getLastName_m());
        dto.setDni(user.getDni());
        dto.setBirthDate(user.getBirthDate());
        dto.setCuil(user.getCuil());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setPhoto_url(user.getPhoto_url());
        dto.setBankAccount(bankAccountMapper.toBankAccountDto(user.getBankAccount()));
        return dto;
    }
}
