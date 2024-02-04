package com.AuthRegLog.Login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLogDto {

	@NotNull
	@JsonProperty("email")
	public String email;

	@NotNull
	@JsonProperty("password")
	public String password;
}
