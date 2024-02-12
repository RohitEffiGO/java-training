package com.learn.app.mapper;

import org.mapstruct.Mapper;

import com.learn.app.dto.AddCategoryDto;
import com.learn.app.dto.AddCourseDto;
import com.learn.app.dto.EnrollCourseDto;
import com.learn.app.model.Category;
import com.learn.app.model.Courses;

@Mapper(componentModel = "spring")
public interface CourseStructMapper {
	Category addCategoryDtoToCategory(AddCategoryDto categoryDto);

	Courses addCourseDtoToCourses(AddCourseDto courseDto);

	Category typeToCategory(String type);

	Courses courseIdDtoToCourse(EnrollCourseDto enrollDto);

	AddCategoryDto categoryToAddCategoryDto(Category category);
}
