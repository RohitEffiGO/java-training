package com.AuthRegLog.Login.dto;

import java.util.List;

import com.AuthRegLog.Login.model.Blog;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlogAllDto {
	@JsonProperty
	private List<Blog> allBlogs;
}
