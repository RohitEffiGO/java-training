package com.learn.app.mapper;

import org.mapstruct.Mapper;

import com.learn.app.dto.RegisterUserDto;
import com.learn.app.model.User;

@Mapper(componentModel = "spring")
public interface UserStructMapper {
	User registerUserDtoToUser(RegisterUserDto registerUser);

	RegisterUserDto userToRegisterUserDto(User user);
}