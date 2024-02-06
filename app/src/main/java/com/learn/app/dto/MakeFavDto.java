package com.learn.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MakeFavDto {
	@JsonProperty("email")
	private String email;

	@JsonProperty("course_id")
	private Long courseId;
}
