package com.AuthRegLog.Login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AuthRegLog.Login.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
	Optional<Blog> findById(Long id);

	List<Blog> findAll();
}
