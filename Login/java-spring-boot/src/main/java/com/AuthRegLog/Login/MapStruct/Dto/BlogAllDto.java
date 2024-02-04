package com.AuthRegLog.Login.MapStruct.Dto;

import java.util.List;

import com.AuthRegLog.Login.model.Blog;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogAllDto {
	@JsonProperty
	private List<Blog> allBlogs;
}
