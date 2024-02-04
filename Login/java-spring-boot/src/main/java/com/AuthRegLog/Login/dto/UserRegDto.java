package com.AuthRegLog.Login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class UserRegDto {
	@Getter
	@JsonProperty("id")
	private Long id;

	@Getter
	@Setter
	@JsonProperty("name")
	private String name;

	@Getter
	@Setter
	@NotNull
	@JsonProperty("email")
	private String email;

	@Getter
	@Setter
	@NotNull
	@JsonProperty("password")
	private String password;

}
