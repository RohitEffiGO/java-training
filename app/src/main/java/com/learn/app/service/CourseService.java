package com.learn.app.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

	private final CourseRepository courseRepo;
	private final CategoryRepository cateRepo;
	private final CoursesCategoryRepository ccRepo;
	private final CourseStructMapper ccMapper;

	public CourseService(CourseRepository courseRepo, CategoryRepository cateRepo, CoursesCategoryRepository ccRepo,
			CourseStructMapper ccMapper) {
		super();
		this.courseRepo = courseRepo;
		this.cateRepo = cateRepo;
		this.ccRepo = ccRepo;
		this.ccMapper = ccMapper;
	}

	String msg = "message";

	public ResponseEntity<Map<String, String>> addCourse(AddCourseDto courseDto) {
		Map<String, String> response = new HashMap<>();

		Optional<Category> categoryObj = cateRepo.findByCategoryType(courseDto.getCategory());

		if (categoryObj.isEmpty()) {
			response.put(msg, "Category not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (courseDto.getCourseName() == null) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Category category = categoryObj.get();

		Courses course = ccMapper.addCourseDtoToCourses(courseDto);
		course.setDateAdded(LocalDate.now());

		CoursesCategory ccObj = new CoursesCategory();
		ccObj.setCategory(category);
		ccObj.setCourse(courseRepo.save(course));
		ccRepo.save(ccObj);

		response.put(msg, "Course added successfully.");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public Optional<List<CoursesCategory>> getCourse(GetCourseDto courseDto) {
		Optional<Category> categoryObj = cateRepo.findByCategoryType(courseDto.getCategory());

		if (categoryObj.isEmpty()) {
			return Optional.of(null);
		}

		Category category = categoryObj.get();

		List<CoursesCategory> courses = ccRepo.findByCategory(category);
		return Optional.of(courses);
	}

	public ResponseEntity<?> getCourseId(Long id, GetCourseDto getCourseDto) {
		try {
			Optional<List<CoursesCategory>> optionalCourses = getCourse(getCourseDto);
			List<CoursesCategory> courses = (optionalCourses.isPresent()) ? optionalCourses.get() : null;

			for (CoursesCategory course : courses) {
				if (course.getId().equals(id)) {
					return new ResponseEntity<>(course, HttpStatus.OK);
				}
			}

			return new ResponseEntity<>(new HashMap<>().put(msg, "Course id does not exists."), HttpStatus.BAD_REQUEST);
		} catch (NullPointerException error) {
			return new ResponseEntity<>(new HashMap<>().put(msg, "does not exists"), HttpStatus.BAD_REQUEST);

		}
	}
}
