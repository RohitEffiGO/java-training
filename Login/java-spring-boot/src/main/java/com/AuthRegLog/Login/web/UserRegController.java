package com.AuthRegLog.Login.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthRegLog.Login.service.UserService;
import com.AuthRegLog.Login.web.dto.UserLogDto;

@RestController
@RequestMapping("/login")
public class UserRegController {

	private UserService userService;

	public UserRegController(UserService userService) {
		super();
		this.userService = userService;
	}

//	@PostMapping("/register")
//	public ResponseEntity<Map<String, String>> regUserAcc(@RequestBody UserRegDto regDto) {
//		Map<String, String> message = new HashMap<>();
//
//		if (regDto.getEmail() == null || regDto.getName() == null || regDto.getPassword() == null) {
//			message.put("Message", "Field Error");
//			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//		}
//
//		try {
//			userService.save(regDto);
//			message.put("Message", "User registered successfully.");
//			return new ResponseEntity<>(message, HttpStatus.OK);
//		} catch (Exception e) {
//			message.put("Message:", "Some error occured.");
//			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@PostMapping
	public ResponseEntity<Map<String, String>> logUserAcc(@RequestBody UserLogDto logDto) {
		Map<String, String> message = new HashMap<>();

		try {
			userService.get(logDto);
			message.put("Message", "Successfully logged in");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			message.put("Message:", "Some error occured.");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
