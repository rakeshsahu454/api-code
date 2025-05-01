package com.apidemo.service;

import com.apidemo.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface RegistrationMapper {

    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);


    RegistrationDto userToUserDTO(Registration registration);
   Registration userDTOToUser(RegistrationDto registrationDto);


}

