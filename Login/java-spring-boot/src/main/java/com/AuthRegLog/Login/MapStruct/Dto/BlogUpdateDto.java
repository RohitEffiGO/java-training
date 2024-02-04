package com.AuthRegLog.Login.MapStruct.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogUpdateDto {
	@JsonProperty("title")
	private String title;

	@JsonProperty("content")
	private String content;
}
