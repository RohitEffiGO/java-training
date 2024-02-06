package com.learn.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddCourseDto {
	@JsonProperty("name")
	private String courseName;

	@JsonProperty("category")
	private String category;
}
