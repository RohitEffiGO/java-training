package com.AuthRegLog.Login.MapStruct.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogDto {

	@NotNull
	@JsonProperty("email")
	public String email;

	@NotNull
	@JsonProperty("password")
	public String password;
}
