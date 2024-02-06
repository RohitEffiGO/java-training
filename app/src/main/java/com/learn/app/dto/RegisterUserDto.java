package com.learn.app.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.app.model.Courses;
import com.learn.app.model.Role;

import lombok.Data;

@Data
public class RegisterUserDto {
	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	private Set<Role> roles;

	private Set<Courses> courses;
}
