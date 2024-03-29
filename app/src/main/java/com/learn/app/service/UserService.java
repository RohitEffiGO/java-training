package com.learn.app.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.learn.app.dto.LoginUserDto;
import com.learn.app.dto.RegisterUserDto;
import com.learn.app.dto.UserEmailDto;
import com.learn.app.mapper.UserStructMapper;
import com.learn.app.model.Role;
import com.learn.app.model.User;
import com.learn.app.repository.RoleRepository;
import com.learn.app.repository.UserRepository;

@Component
public class UserService implements UserDetailsService {
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	private final UserStructMapper userMapper;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public UserService(UserRepository userRepo, RoleRepository roleRepo, UserStructMapper userMapper,
			@Lazy AuthenticationManager authenticationManager, JwtService jwtService) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.userMapper = userMapper;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.jwtService = jwtService;
	}

	Map<String, String> response = new HashMap<>();
	String msg = "message";

	public ResponseEntity<Map<String, String>> registerUser(RegisterUserDto registerUser) {
		if (registerUser.getEmail() == null || registerUser.getPassword() == null) {
			response.put(msg, "fields cannot be null");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Optional<Role> defaultRole = roleRepo.findByRoleType("LEARNER");

		if (defaultRole.isEmpty()) {
			response.put(msg, "Role does not exists.");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

		Set<Role> roles = new HashSet<>();
		roles.add(defaultRole.get());
		registerUser.setRoles(roles);
		registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));

		User newUser = userMapper.registerUserDtoToUser(registerUser);

		try {
			User user = userRepo.save(newUser);
			String token = jwtService.generateToken(user);
			response.put(msg, token);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException exception) {
			response.put("Error", "User already exists.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		catch (Exception e) {
			response.put("Error", e.toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Map<String, String>> loginUser(LoginUserDto loginDto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		try {
			User user = userRepo.findByEmail(loginDto.getEmail());
			String token = jwtService.generateToken(user);
			Map<String, String> reply = new HashMap<>();
			reply.put("message", token);
			return new ResponseEntity<>(reply, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put(msg, "Authentication error");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username);
	}

	public ResponseEntity<Map<String, String>> escalateRole(UserEmailDto userEmailDto) {

		if (userEmailDto.getEmail() != null) {
			userRepo.escalateRole(userEmailDto.getEmail());
			response.put(msg, "role escalated");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.ACCEPTED);
		}
		response.put(msg, "something went wrong");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
