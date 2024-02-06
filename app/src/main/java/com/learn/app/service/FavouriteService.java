package com.learn.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.MakeFavDto;
import com.learn.app.model.Courses;
import com.learn.app.model.User;
import com.learn.app.repository.CourseRepository;
import com.learn.app.repository.UserRepository;

@Component
public class FavouriteService {
	@Autowired
	CourseRepository courseRepo;

	@Autowired
	UserRepository userRepo;

	public ResponseEntity<Map<String, String>> makeFavourite(MakeFavDto makeFavDto) {
		Optional<Courses> course = courseRepo.findById(makeFavDto.getCourseId());
		Map<String, String> response = new HashMap<>();
		String email = makeFavDto.getEmail();
		User user = userRepo.findByEmail(email);

		if (user == null || course.isEmpty()) {
			response.put("Message", "Validation error");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Set<Courses> fav = user.getFav();
		fav.add(course.get());
		user.setFav(fav);
		userRepo.save(user);

		response.put("Message", "Course added to favourites.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
