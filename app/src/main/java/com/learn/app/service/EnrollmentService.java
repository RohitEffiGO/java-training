package com.learn.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.EnrollCourseDto;
import com.learn.app.model.Courses;
import com.learn.app.model.User;
import com.learn.app.repository.CourseRepository;
import com.learn.app.repository.UserRepository;

import jakarta.validation.Valid;

@Component
public class EnrollmentService {
	private final UserRepository userRepo;
	private final JwtService jwtService;
	private final CourseRepository courseRepo;

	public EnrollmentService(UserRepository userRepo, JwtService jwtService, CourseRepository courseRepo) {
		super();
		this.userRepo = userRepo;
		this.jwtService = jwtService;
		this.courseRepo = courseRepo;
	}

	String msg = "message";
	String notFound = "course not found";

	public ResponseEntity<Map<String, String>> enrollToUser(String header, EnrollCourseDto enrollDto) {
		Map<String, String> response = new HashMap<>();

		try {
			String email = jwtService.extractUsername(header.substring(7));
			User user = userRepo.findByEmail(email);
			Optional<Courses> enrollCourse = courseRepo.findById(enrollDto.getCourseId());

			if (enrollCourse.isEmpty())
				throw new ObjectNotFoundException(enrollCourse, notFound);

			Set<Courses> coursesSubscribed = user.getSubscribed();

			if (userRepo.findBySubscribed(enrollCourse.get().getId()).isPresent()) {
				throw new IllegalStateException();
			}

			coursesSubscribed.add(enrollCourse.get());
			user.setSubscribed(coursesSubscribed);
			userRepo.save(user);
		} catch (ObjectNotFoundException error) {
			response.put(msg, notFound);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (IllegalStateException error) {
			response.put(msg, "Course already subscribed.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception error) {
			response.put(msg, error.toString());
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}

		response.put(msg, "Course subscribed successfully.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getSubscribed(String header) {
		Map<String, Set<Courses>> response = new HashMap<>();

		try {
			String email = jwtService.extractUsername(header.substring(7));
			User user = userRepo.findByEmail(email);
			Set<Courses> subbedCourses = user.getSubscribed();
			response.put("courses", subbedCourses);

		} catch (Exception error) {
			return new ResponseEntity<>(new HashMap<>().put(msg, error.toString()), HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, String>> unEnrollToUser(String header, @Valid EnrollCourseDto unenrollDto) {
		Map<String, String> response = new HashMap<>();
		try {
			String email = jwtService.extractUsername(header.substring(7));
			User user = userRepo.findByEmail(email);
			Optional<Courses> enrollCourse = courseRepo.findById(unenrollDto.getCourseId());

			if (enrollCourse.isEmpty())
				throw new ObjectNotFoundException(enrollCourse, notFound);

			Set<Courses> coursesSubscribed = user.getSubscribed();
			for (Courses course : coursesSubscribed) {
				if (course.getId().equals(enrollCourse.get().getId())) {
					coursesSubscribed.remove(course);
					user.setSubscribed(coursesSubscribed);
					userRepo.save(user);
					response.put(msg, "Course unsubscribed successfully.");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}

		} catch (ObjectNotFoundException error) {
			response.put(msg, notFound);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception error) {
			response.put(msg, error.toString());
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}

		response.put(msg, "Course not subscribed.");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
