package com.learn.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.TakeRoleDto;
import com.learn.app.model.User;
import com.learn.app.repository.UserRepository;

@Component
public class AdminService {
	private final UserRepository userRepo;

	public AdminService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	Map<String, List<User>> response = new HashMap<>();
	String msg = "message";

	public ResponseEntity<Map<String, List<User>>> getUsersByRole(TakeRoleDto takeRoleDto) {

		try {
			List<User> equivalentUsers = userRepo.getAllByRole(takeRoleDto.getRole());
			response.put(msg, equivalentUsers);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception error) {
			response.put(msg, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Map<String, List<User>>> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		response.put(msg, allUsers);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
