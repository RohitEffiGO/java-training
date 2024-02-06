package com.learn.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.AddCategoryDto;
import com.learn.app.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@PostMapping("/add")
	public ResponseEntity<?> postAddCategory(@Valid @RequestBody AddCategoryDto addCategory) {
		return categoryService.addCategory(addCategory);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllCategory() {
		return categoryService.getCategory();
	}

}
