package com.learn.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn.app.dto.AddCategoryDto;
import com.learn.app.mapper.CourseStructMapper;
import com.learn.app.model.Category;
import com.learn.app.repository.CategoryRepository;

@Component
public class CategoryService {
	@Autowired
	CategoryRepository cateRepo;

	@Autowired
	CourseStructMapper ccMapper;

	public ResponseEntity<?> addCategory(AddCategoryDto addCategory) {
		Map<String, String> response = new HashMap<>();

		try {
			Category categoryInit = ccMapper.addCategoryDtoToCategory(addCategory);

			if (categoryInit == null) {
				response.put("Message", "Something went wrong.");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			cateRepo.save(categoryInit);
			response.put("Message", "Category added.");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("Message", "Category already exists.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getCategory() {
		Map<String, List<Category>> response = new HashMap<>();
		response.put("Message", cateRepo.findAll());
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
