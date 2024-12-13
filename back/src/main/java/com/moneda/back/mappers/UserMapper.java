package com.moneda.back.mappers;

import com.moneda.back.dto.BankAccountDto;
import com.moneda.back.dto.CurrencyDto;
import com.moneda.back.dto.UserDto;
import com.moneda.back.entities.Currency;
import com.moneda.back.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    @Lazy
    private final BankAccountMapper bankAccountMapper;
    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setUser_id(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName_p(user.getLastName_p());
        dto.setLastName_m(user.getLastName_m());
        dto.setAddress(user.getAddress());
        dto.setBirthDate(user.getBirthDate());
        dto.setCuil(user.getCuil());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setPhoto_url(user.getPhoto_url());
        List<BankAccountDto> bankAccountDtos = user.getBankAccounts().stream()
                .map(bankAccountMapper::toBankAccountDto)
                .collect(Collectors.toList());
        dto.setBankAccounts(bankAccountDtos);
        return dto;
    }
}
