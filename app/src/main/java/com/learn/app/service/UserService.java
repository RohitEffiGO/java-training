package com.learn.app.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.LoginUserDto;
import com.learn.app.dto.RegisterUserDto;
import com.learn.app.mapper.UserStructMapper;
import com.learn.app.model.Role;
import com.learn.app.model.User;
import com.learn.app.repository.RoleRepository;
import com.learn.app.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserStructMapper userMapper;

	public ResponseEntity<?> registerUser(RegisterUserDto registerUser) {
		Map<String, String> response = new HashMap<>();

		Optional<Role> defaultRole = roleRepo.findByRoleType("LEARNER");

		Set<Role> roles = new HashSet<>();
		roles.add(defaultRole.get());
		registerUser.setRoles(roles);

		User newUser = userMapper.registerUserDtoToUser(registerUser);

		try {
			userRepo.save(newUser);
			response.put("Message", "User registered successfully.");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception pSQLException) {
			response.put("Error", "User already exists.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> loginUser(LoginUserDto loginDto) {
		Map<String, String> response = new HashMap<>();

		try {
			User user = userRepo.findByEmail(loginDto.getEmail());
			RegisterUserDto userDetails = userMapper.userToRegisterUserDto(user);

			if (userDetails.getPassword().equals(loginDto.getPassword())) {
				response.put("Message", "User logged in successfully.");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			} else {
				response.put("Message", "User authentication failed.");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.put("Message", "Authentication error");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
