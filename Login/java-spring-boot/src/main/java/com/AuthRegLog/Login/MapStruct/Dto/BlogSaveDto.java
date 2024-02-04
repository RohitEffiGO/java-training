package com.AuthRegLog.Login.MapStruct.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlogSaveDto {
	@JsonProperty("title")
	private String title;

	@JsonProperty("content")
	private String content;
}
