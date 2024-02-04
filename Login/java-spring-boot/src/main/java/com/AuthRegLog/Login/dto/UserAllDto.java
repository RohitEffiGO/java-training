package com.AuthRegLog.Login.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAllDto {
	@NotNull
	@JsonProperty("id")
	private Long id;

	@NotNull
	@JsonProperty("name")
	private String name;

	@NotNull
	@JsonProperty("email")
	private String email;

	@JsonProperty("roles")
	private List<RoleDto> roles;
}
