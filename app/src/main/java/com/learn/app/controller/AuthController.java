package com.learn.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.LoginUserDto;
import com.learn.app.dto.RegisterUserDto;
import com.learn.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> performRegister(@Valid @RequestBody RegisterUserDto registerUser) {
		return userService.registerUser(registerUser);
	}

	@PostMapping("/login")
	public ResponseEntity<?> performLogin(@Valid @RequestBody LoginUserDto loginUser) {
		return userService.loginUser(loginUser);
	}
}
