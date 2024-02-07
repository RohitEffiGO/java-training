package com.learn.app.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.AddCourseDto;
import com.learn.app.dto.GetCourseDto;
import com.learn.app.mapper.CourseStructMapper;
import com.learn.app.model.Category;
import com.learn.app.model.Courses;
import com.learn.app.model.CoursesCategory;
import com.learn.app.repository.CategoryRepository;
import com.learn.app.repository.CourseRepository;
import com.learn.app.repository.CoursesCategoryRepository;

@Component
public class CourseService {
	@Autowired
	CourseRepository courseRepo;

	@Autowired
	CategoryRepository cateRepo;

	@Autowired
	CoursesCategoryRepository ccRepo;

	@Autowired
	CourseStructMapper ccMapper;

	public ResponseEntity<?> addCourse(AddCourseDto courseDto) {
		Map<String, String> response = new HashMap<>();

		Optional<Category> categoryObj = cateRepo.findByCategoryType(courseDto.getCategory());

		if (categoryObj.isEmpty()) {
			response.put("Message", "Category not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Category category = categoryObj.get();

		Courses course = ccMapper.addCourseDtoToCourses(courseDto);
		course.setDateAdded(LocalDate.now());

		CoursesCategory ccObj = new CoursesCategory();
		ccObj.setCategory(category);
		ccObj.setCourse(courseRepo.save(course));
		ccRepo.save(ccObj);

		response.put("Message", "Course added successfully.");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public Optional<List<CoursesCategory>> getCourse(GetCourseDto courseDto) {
		Map<String, String> response = new HashMap<>();

		Optional<Category> categoryObj = cateRepo.findByCategoryType(courseDto.getCategory());

		if (categoryObj.isEmpty()) {
			return Optional.of(null);
		}

		Category category = categoryObj.get();

		List<CoursesCategory> courses = ccRepo.findByCategory(category);

		Optional<List<CoursesCategory>> optinalList = Optional.of(courses);
		return optinalList;
	}

	public ResponseEntity<?> getCourseId(Long id, GetCourseDto getCourseDto) {
		List<CoursesCategory> courses = getCourse(getCourseDto).get();

		for (CoursesCategory course : courses) {
			if (course.getId().equals(id)) {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(new HashMap<>().put("Message", "Course id does not exists."),
				HttpStatus.BAD_REQUEST);
	}
}
