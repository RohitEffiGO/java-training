package com.AuthRegLog.Login.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthRegLog.Login.MapStruct.Dto.BlogSaveDto;
import com.AuthRegLog.Login.MapStruct.Mapper.BlogStructMapper;
import com.AuthRegLog.Login.model.Blog;
import com.AuthRegLog.Login.repository.BlogRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blog")
public class BlogCrudController {

	@Autowired
	private BlogStructMapper blogStructMapper;

	private BlogRepository blogRepository;

	BlogCrudController(BlogStructMapper blogStructMapper, BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
		this.blogStructMapper = blogStructMapper;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveBlog(@Valid @RequestBody BlogSaveDto blogSaveDto) {
		Map<String, String> message = new HashMap<>();
		Blog blogSave = blogStructMapper.blogSaveDtoToBlog(blogSaveDto);
		blogRepository.save(blogSave);

		message.put("Message", "Blog created successfully.");
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<?> getAllBlogs() {
		Map<Long, Map<?, ?>> message = new HashMap<>();
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			Map<String, String> mapBlogTemp = new HashMap<>();
			BlogSaveDto blogData = blogStructMapper.blogToBlogSaveDto(blog);
			mapBlogTemp.put("Title", blogData.getTitle());
			mapBlogTemp.put("Content", blogData.getContent());
			message.put(blog.getId(), mapBlogTemp);
		}

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getSpecificBlog(@PathVariable Long id) {
		Map<Long, Map<?, ?>> response = new HashMap<>();
		Optional<Blog> blog = blogRepository.findById(id);
		if (blog.isEmpty())
			return new ResponseEntity<>(new HashMap<>().put("Message", "No blog found"), HttpStatus.NO_CONTENT);
		BlogSaveDto blogData = blogStructMapper.blogToBlogSaveDto(blog.get());
		Map<String, String> temp = new HashMap<>();
		temp.put("Title", blogData.getTitle());
		temp.put("Content", blogData.getContent());
		response.put(id, temp);

		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBlogData(@PathVariable Long id, @Valid @RequestBody BlogSaveDto blogUpdate) {
		Map<String, String> response = new HashMap<>();
		Optional<Blog> blog = blogRepository.findById(id);

		if (!blog.isPresent()) {
			response.put("Message", "Blog not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Blog blogData = blog.get();
		blogData.setTitle(blogUpdate.getTitle());
		blogData.setContent(blogUpdate.getContent());
		blogRepository.save(blogData);
		response.put("Message", "Blog updated successfully.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		Optional<Blog> blog = blogRepository.findById(id);

		if (!blog.isPresent()) {
			response.put("Message", "Blog not found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		blogRepository.delete(blog.get());

		response.put("Message", "Blog removed successfully.");
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
}
