package com.AuthRegLog.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.AuthRegLog.Login.dto.BlogSaveDto;
import com.AuthRegLog.Login.mapper.BlogStructMapper;
import com.AuthRegLog.Login.model.Blog;
import com.AuthRegLog.Login.repository.BlogRepository;

@Component
public class BlogService {
	@Autowired
	private BlogStructMapper blogStructMapper;

	@Autowired
	private BlogRepository blogRepository;

	public void saveBlogService(BlogSaveDto blogSaveDto) {
		Blog blogSave = blogStructMapper.blogSaveDtoToBlog(blogSaveDto);
		blogRepository.save(blogSave);
	}

	public Map<Long, Map<?, ?>> getAllBlogService() {
		Map<Long, Map<?, ?>> response = new HashMap<>();

		List<Blog> blogs = blogRepository.findAll();

		for (Blog blog : blogs) {
			Map<String, String> mapBlogTemp = new HashMap<>();
			BlogSaveDto blogData = blogStructMapper.blogToBlogSaveDto(blog);
			mapBlogTemp.put("Title", blogData.getTitle());
			mapBlogTemp.put("Content", blogData.getContent());
			response.put(blog.getId(), mapBlogTemp);
		}

		return response;
	}

	public Map<String, String> getSpecificBlogService(Long id) {
		Optional<Blog> blog = blogRepository.findById(id);

		if (blog.isEmpty()) {
			return null;
		}

		BlogSaveDto blogData = blogStructMapper.blogToBlogSaveDto(blog.get());
		Map<String, String> response = new HashMap<>();
		response.put("Title", blogData.getTitle());
		response.put("Content", blogData.getContent());

		return response;
	}

	public boolean updateBlogService(Long id, BlogSaveDto blogUpdate) {
		Optional<Blog> blog = blogRepository.findById(id);

		if (!blog.isPresent()) {
			return false;
		}

		Blog blogData = blog.get();
		blogData.setTitle(blogUpdate.getTitle());
		blogData.setContent(blogUpdate.getContent());
		blogRepository.save(blogData);

		return true;
	}

	public boolean deleteBlogService(Long id) {
		Optional<Blog> blog = blogRepository.findById(id);

		if (!blog.isPresent()) {
			return false;
		}

		blogRepository.delete(blog.get());
		return true;
	}
}
