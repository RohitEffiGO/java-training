package com.AuthRegLog.Login.MapStruct.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
