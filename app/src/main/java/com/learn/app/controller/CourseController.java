package com.learn.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.AddCourseDto;
import com.learn.app.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
	CourseService courseSevice;

	@PostMapping("/add")
	public ResponseEntity<?> addCourses(@Valid @RequestBody AddCourseDto courseDto) {
		return courseSevice.addCourse(courseDto);
	}
}
