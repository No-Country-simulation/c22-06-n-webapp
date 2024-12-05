package com.moneda.back.mappers;

import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.dto.UserDto;
import com.moneda.back.entities.Currency;
import com.moneda.back.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setFirstName(dto.getFirstName());
        dto.setLastName_p(dto.getLastName_p());
        dto.setLastName_m(dto.getLastName_m());
        dto.setDni(dto.getDni());
        dto.setBirthDate(dto.getBirthDate());
        dto.setCuil(dto.getCuil());
        dto.setEmail(dto.getEmail());
        dto.setPhone(dto.getPhone());
        dto.setPhoto_url(dto.getPhoto_url());
        return dto;
    }
}
