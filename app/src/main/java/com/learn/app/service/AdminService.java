package com.learn.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.TakeRoleDto;
import com.learn.app.model.Role;
import com.learn.app.model.User;
import com.learn.app.repository.RoleRepository;
import com.learn.app.repository.UserRepository;

@Component
public class AdminService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	public ResponseEntity<?> getUserByRole(TakeRoleDto takeRoleDto) {
		Map<String, List<User>> response = new HashMap<>();

		try {
			System.out.println(takeRoleDto.getRole());
			Optional<Role> role = roleRepo.findByRoleType(takeRoleDto.getRole());

			if (role.isEmpty()) {
				throw new NullPointerException();
			}

			List<User> allUsersByRole = userRepo.findByRoles(role.get());
			response.put("message", allUsersByRole);

		} catch (NullPointerException error) {
			response.put("message", null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception error) {

		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, List<User>>> getAllUsers() {
		Map<String, List<User>> response = new HashMap<>();
		List<User> allUsers = userRepo.findAll();
		response.put("message", allUsers);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
