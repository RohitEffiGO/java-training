package com.AuthRegLog.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.AuthRegLog.Login.controller.BlogCrudController;
import com.AuthRegLog.Login.mapper.BlogStructMapper;
import com.AuthRegLog.Service.BlogService;

//@ComponentScan({ "com.AuthRegLog.Login.Service"})
@SpringBootApplication
@ComponentScan(basePackageClasses = {BlogCrudController.class,
		BlogService.class,
		BlogStructMapper.class})
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
