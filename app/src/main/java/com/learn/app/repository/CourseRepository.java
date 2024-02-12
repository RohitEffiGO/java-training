package com.learn.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.app.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {

	@Query(value = "SELECT * FROM courses WHERE id = ?", nativeQuery = true)
	Optional<Courses> findById(Long id);
}
