package com.AuthRegLog.Login.mapper;

import org.mapstruct.Mapper;

import com.AuthRegLog.Login.dto.UserLogDto;
import com.AuthRegLog.Login.dto.UserRegDto;
import com.AuthRegLog.Login.model.User;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	// Take user give its dto.
	UserRegDto userToUserDto(User user);

	User userRegDtoToUser(UserRegDto userRegDto);

	// Take user dto and give user.
	User userLogDtoToUser(UserLogDto userDto);

	UserLogDto userToUserLogDto(User user);

	UserLogDto emailToUserLogDto(String email);

	String userLogDtoToEmail(UserLogDto userDto);

	UserRegDto userLogDtoToUserRegDto(UserLogDto userDto);

}
