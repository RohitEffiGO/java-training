package com.AuthRegLog.Login.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthRegLog.Login.model.User;
import com.AuthRegLog.Login.service.UserService;
import com.AuthRegLog.Login.web.dto.UserLogDto;
import com.AuthRegLog.Login.web.dto.UserRegDto;

@RestController
@RequestMapping("/api/auth")
public class UserRegController {

	private UserService userService;

	public UserRegController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> regUserAcc(@RequestBody UserRegDto regDto) {
		Map<String, String> message = new HashMap<>();

		if (regDto.getEmail() == null || regDto.getName() == null || regDto.getPassword() == null) {
			message.put("Message", "Field Error");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

		try {
			userService.save(regDto);
			message.put("Message", "User registered successfully.");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			message.put("Message:", "Some error occured.");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> logUserAcc(@RequestBody UserLogDto logDto) {
		Map<String, String> message = new HashMap<>();

		try {
			User userDetials = userService.get(logDto);
			String pass_val = userDetials.getPassword();

			if (pass_val.equals(logDto.getPassword())) {
				message.put("Message", "Successfully logged in");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
			
			message.put("Message", "User Creds does not match.");
			return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
					
		} catch (Exception e) {
			message.put("Message:", "Some error occured." + e);
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
