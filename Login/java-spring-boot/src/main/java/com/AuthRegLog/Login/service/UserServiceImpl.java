package com.AuthRegLog.Login.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AuthRegLog.Login.model.Role;
import com.AuthRegLog.Login.model.User;
import com.AuthRegLog.Login.repository.UserRepository;
import com.AuthRegLog.Login.web.dto.UserLogDto;
import com.AuthRegLog.Login.web.dto.UserRegDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository repo;
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public User save(UserRegDto regDto)
	{
		List<Role> arr = Arrays.asList(new Role("USER"));
		User user = new User(
				regDto.getName(),
				regDto.getEmail(),
				passwordEncoder.encode(regDto.getPassword()),
				arr);
		return userRepo.save(user);
	}
	
	@Override
	public User get(UserLogDto logDto) {
		return repo.findByEmail(logDto.getEmail());
	}
}
