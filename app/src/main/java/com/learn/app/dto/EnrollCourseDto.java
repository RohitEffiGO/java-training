package com.learn.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnrollCourseDto {
	@JsonProperty("course_id")
	Long courseId;
}
