package com.AuthRegLog.Login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthRegLog.Login.dto.UserLogDto;
import com.AuthRegLog.Login.dto.UserRegDto;
import com.AuthRegLog.Login.mapper.MapStructMapper;
import com.AuthRegLog.Login.model.User;
import com.AuthRegLog.Login.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserRegController {

	@Autowired
	private MapStructMapper mapstructMapper;

	private UserRepository userRepository;

	public UserRegController(MapStructMapper mapStructMapper, UserRepository userRepository) {
		this.mapstructMapper = mapStructMapper;
		this.userRepository = userRepository;
	}

	@GetMapping("/all")
	public Map<Long, Map<String, String>> getAllUsers() {
		Map<Long, Map<String, String>> getUserByName = new HashMap<>();

		List<User> allUsers = userRepository.findAll();

		Long i = 0L;
		for (User user : allUsers) {
			UserRegDto userDto = mapstructMapper.userToUserDto(user);
			Map<String, String> temp = new HashMap<>();
			temp.put("Name", userDto.getName());
			temp.put("Email", userDto.getEmail());
			getUserByName.put(i++, temp);
		}

		return getUserByName;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<?, ?>> regUserAcc(@Valid @RequestBody UserRegDto regDto) {
		Map<String, String> message = new HashMap<>();
		userRepository.save(mapstructMapper.userRegDtoToUser(regDto));
		message.put("Message", "User successfully registered.");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> logUserAcc(@Valid @RequestBody UserLogDto logDto) {
		Map<String, String> message = new HashMap<>();

		try {
			User user = userRepository.findByEmail(logDto.getEmail());

			if (user == null) {
				message.put("Message", "User does not exists.");
				return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
			}

			UserRegDto userDetails = mapstructMapper.userToUserDto(user);

			if (!userDetails.getPassword().equals(logDto.getPassword())) {
				message.put("Message", "User credentials wrong.");
				return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {

			message.put("Error", e.toString());
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		message.put("Message", "User successfully logged in.");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
