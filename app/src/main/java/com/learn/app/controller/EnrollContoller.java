package com.learn.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	EnrollmentService enrollService;

	@PostMapping("/subscribe")
	public ResponseEntity<?> subCourse(@RequestHeader(name = "Authorization") String header,
			@RequestBody @Valid EnrollCourseDto enrollDto) {
		return enrollService.enrollToUser(header, enrollDto);
	}

}
