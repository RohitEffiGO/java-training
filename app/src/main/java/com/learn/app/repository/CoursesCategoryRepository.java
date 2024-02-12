package com.learn.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.app.model.Category;
import com.learn.app.model.CoursesCategory;

public interface CoursesCategoryRepository extends JpaRepository<CoursesCategory, Long> {

	@Query(name = "SELECT * FROM cc_mapper WHERE cc_mapper.category_id = ?", nativeQuery = true)
	List<CoursesCategory> findByCategory(Category category);
}
