package com.Buchverwaltung.mapper;

import com.Buchverwaltung.dto.UserRegistrationDto;
import com.Buchverwaltung.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegistrationDto dto);

    UserRegistrationDto toDto(User user);
}
