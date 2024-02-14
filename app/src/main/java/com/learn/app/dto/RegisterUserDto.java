package com.learn.app.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.app.model.Courses;
import com.learn.app.model.Role;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDto {
	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	@NotNull
	private String email;

	@JsonProperty("password")
	@NotNull
	private String password;

	private Set<Role> roles;

	private Set<Courses> courses;
}
