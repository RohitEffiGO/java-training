package com.learn.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddCategoryDto {
	@JsonProperty("type")
	private String categoryType;
}
