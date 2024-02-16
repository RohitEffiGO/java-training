package com.learn.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.TakeRoleDto;
import com.learn.app.service.AdminService;

@RestController
@RequestMapping("/api/user")
public class UserDetailController {

	@Autowired
	AdminService adminService;

	@PostMapping("/roles")
	public ResponseEntity<?> getByRoles(@RequestBody TakeRoleDto takeRoleDto) {
		return adminService.getUserByRole(takeRoleDto);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		return adminService.getAllUsers();
	}
}
