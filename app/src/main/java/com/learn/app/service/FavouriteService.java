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
import com.learn.app.dto.UserFavDto;
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
		Map<String, String> response = new HashMap<>();

		Optional<Courses> course = courseRepo.findById(makeFavDto.getCourseId());
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

	public ResponseEntity<Map<String, String>> makeUnFavourite(MakeFavDto makeUnFavDto) {
		Map<String, String> response = new HashMap<>();

		Optional<Courses> course = courseRepo.findById(makeUnFavDto.getCourseId());
		String email = makeUnFavDto.getEmail();
		User user = userRepo.findByEmail(email);

		if (user == null || course.isEmpty()) {
			response.put("Message", "Validation error");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Set<Courses> fav = user.getFav();

		try {
			fav.remove(course.get());
		} catch (Exception e) {
			response.put("Error", e.toString());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		user.setFav(fav);
		userRepo.save(user);

		response.put("Message", "Course removed from favourites.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllFav(UserFavDto userFavDto) {
		Map<String, Object> response = new HashMap<>();

		User user = userRepo.findByEmail(userFavDto.getEmail());

		if (user == null) {
			response.put("Message", "Email does not exists.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		response.put("Message", user.getFav());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
