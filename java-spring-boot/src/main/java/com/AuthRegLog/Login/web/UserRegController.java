package com.AuthRegLog.Login.web;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthRegLog.Login.service.UserService;
import com.AuthRegLog.Login.web.dto.UserRegDto;

@RestController
@RequestMapping("/register")
public class UserRegController {

	private UserService userService;

	public UserRegController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Map<String,String>> regUserAcc(@RequestBody UserRegDto regDto) {
		Map<String,String> message = new HashMap<>();
		System.out.println(regDto);
		if(regDto.getEmail() == null || regDto.getName() == null || regDto.getPassword() == null)
		{
			message.put("Message", "Field Error");
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
		String hashedString = Hashing.sha256().hashString(regDto.getPassword(), StandardCharsets.UTF_8).toString();
		regDto.setPassword(hashedString);
		
		try {
			userService.save(regDto);
			message.put("Message", "User registered successfully.");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			message.put("Message:", "Some error occured.");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
