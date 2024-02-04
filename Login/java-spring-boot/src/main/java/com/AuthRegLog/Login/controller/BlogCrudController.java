package com.AuthRegLog.Login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthRegLog.Login.dto.BlogSaveDto;
import com.AuthRegLog.Service.BlogService;

import jakarta.validation.Valid;

@Component
@RestController
@RequestMapping("/api/blog")
public class BlogCrudController {
	@Autowired
	private BlogService service;

	
	public BlogCrudController(BlogService service) {
		super();
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveBlog(@Valid @RequestBody BlogSaveDto blogSaveDto) {
		Map<String, String> message = new HashMap<>();
		service.saveBlogService(blogSaveDto);
		message.put("Message", "Blog created successfully.");
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<?> getAllBlogs() {
		Map<Long, Map<?, ?>> message = service.getAllBlogService();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getSpecificBlog(@PathVariable Long id) {
		Map<Long, Map<?, ?>> message = new HashMap<>();
		Map<?, ?> getBlog = service.getSpecificBlogService(id);

		if (getBlog == null)
			return new ResponseEntity<>(new HashMap<>().put("Message", "No blog found"), HttpStatus.NO_CONTENT);

		message.put(id, getBlog);
		return new ResponseEntity<>(message, HttpStatus.FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBlogData(@PathVariable Long id, @Valid @RequestBody BlogSaveDto blogUpdate) {
		Map<String, String> response = new HashMap<>();
		boolean updateStatus = service.updateBlogService(id, blogUpdate);

		if (!updateStatus) {
			response.put("Message", "Blog not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		response.put("Message", "Blog updated successfully.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		boolean performDelete = service.deleteBlogService(id);

		if (!performDelete) {
			response.put("Message", "Blog not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		response.put("Message", "Blog removed successfully.");
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
}
