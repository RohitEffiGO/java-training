package com.AuthRegLog.Login.service;


import com.AuthRegLog.Login.model.User;
import com.AuthRegLog.Login.web.dto.UserLogDto;
import com.AuthRegLog.Login.web.dto.UserRegDto;

public interface UserService {
	User save(UserRegDto regDto);
	User get(UserLogDto regDto);
}