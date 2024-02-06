package com.learn.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.AddCourseDto;
import com.learn.app.dto.GetCourseDto;
import com.learn.app.model.CoursesCategory;
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

	@PostMapping("/fetch")
	public ResponseEntity<?> fetchCourses(@Valid @RequestBody GetCourseDto getCourseDto) {
		Optional<List<CoursesCategory>> response = courseSevice.getCourse(getCourseDto);

		if (response.isEmpty()) {
			return new ResponseEntity<>(new HashMap<>().put("Message", "Category does not exsits"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@PostMapping("/fetch/{id}")
	public ResponseEntity<?> fetchCoursesId(@Valid @PathVariable Long id, @RequestBody GetCourseDto getCourseDto) {
		return courseSevice.getCourseId(id, getCourseDto);
	}
}
