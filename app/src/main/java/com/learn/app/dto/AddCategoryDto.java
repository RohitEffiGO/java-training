package com.learn.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddCategoryDto {
	private String id;

	@JsonProperty("category_name")
	private String categoryType;
}
