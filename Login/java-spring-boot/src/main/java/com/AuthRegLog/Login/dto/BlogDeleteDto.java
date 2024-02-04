package com.AuthRegLog.Login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlogDeleteDto {
	@JsonProperty("id")
	private Long id;
}
