package com.learn.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserEmailDto {

	@JsonProperty("email")
	private String email;
}
