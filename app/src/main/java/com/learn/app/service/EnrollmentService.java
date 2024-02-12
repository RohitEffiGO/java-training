package com.learn.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.EnrollCourseDto;
import com.learn.app.mapper.CourseStructMapper;
import com.learn.app.model.Courses;
import com.learn.app.model.User;
import com.learn.app.repository.CourseRepository;
import com.learn.app.repository.UserRepository;

@Component
public class EnrollmentService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	JwtService jwtService;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	CourseStructMapper courseMapper;

	public ResponseEntity<Map<String, String>> enrollToUser(String header, EnrollCourseDto enrollDto) {
		Map<String, String> response = new HashMap<>();

		try {
			String email = jwtService.extractUsername(header.substring(7));
			User user = userRepo.findByEmail(email);
			Optional<Courses> enrollCourse = courseRepo.findById(enrollDto.getCourseId());

			if (enrollCourse.isEmpty())
				throw new ObjectNotFoundException(enrollCourse, "Course does not exists.");

			Set<Courses> coursesSubscribed = user.getSubscribed();

			for (Courses course : coursesSubscribed)
				if (course.getId().equals(enrollCourse.get().getId()))
					throw new IllegalStateException();

			coursesSubscribed.add(enrollCourse.get());
			user.setSubscribed(coursesSubscribed);
			userRepo.save(user);
		} catch (ObjectNotFoundException error) {
			response.put("message", "Course does not exists.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (IllegalStateException error) {
			response.put("message", "Course already subscribed.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception error) {
			response.put("message", error.toString());
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}

		response.put("message", "Course subscribed successfully.");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
}
