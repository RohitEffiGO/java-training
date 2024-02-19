package com.learn.app.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.EnrollCourseDto;
import com.learn.app.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enroll")
public class EnrollContoller {
	public EnrollContoller(EnrollmentService enrollService) {
		super();
		this.enrollService = enrollService;
	}

	private final EnrollmentService enrollService;

	@PostMapping("/subscribe")
	public ResponseEntity<Map<String, String>> subCourse(@RequestHeader(name = "Authorization") String header,
			@RequestBody @Valid EnrollCourseDto enrollDto) {
		return enrollService.enrollToUser(header, enrollDto);
	}

	@PostMapping("/subscribed")
	public ResponseEntity<?> getCourses(@RequestHeader(name = "Authorization") String header) {
		return enrollService.getSubscribed(header);
	}

	@PostMapping("/unsubscribe")
	public ResponseEntity<Map<String, String>> unSubCourse(@RequestHeader(name = "Authorization") String header,
			@RequestBody @Valid EnrollCourseDto unenrollDto) {
		return enrollService.unEnrollToUser(header, unenrollDto);
	}
}
