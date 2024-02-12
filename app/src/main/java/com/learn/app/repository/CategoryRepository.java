package com.learn.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(name = "SELECT * FROM course_category WHERE type = ?")
	Optional<Category> findByCategoryType(String category);
}
