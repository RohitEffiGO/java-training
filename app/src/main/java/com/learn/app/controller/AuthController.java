package com.learn.app.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.LoginUserDto;
import com.learn.app.dto.RegisterUserDto;
import com.learn.app.service.UserService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> performRegister(@NotNull @RequestBody RegisterUserDto registerUser) {
		return userService.registerUser(registerUser);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> performLogin(@RequestBody LoginUserDto loginUser) {
		return userService.loginUser(loginUser);
	}
}
